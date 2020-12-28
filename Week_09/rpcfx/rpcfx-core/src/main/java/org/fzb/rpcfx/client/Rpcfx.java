package org.fzb.rpcfx.client;

import com.alibaba.fastjson.parser.ParserConfig;
import lombok.extern.slf4j.Slf4j;
import org.fzb.rpcfx.api.Filter;
import org.fzb.rpcfx.api.LoadBalancer;
import org.fzb.rpcfx.api.Router;
import org.fzb.rpcfx.api.ServiceProviderDesc;
import org.fzb.rpcfx.registry.RegistryClient;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Rpcfx
 *
 * @author fengzhenbing
 */
@Slf4j
public class Rpcfx  {
    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        // ParserConfig.getGlobalInstance().addAccept("org.fzb");
    }

    private static final RpcfxProxy defaultProxy = new JdkProxy();


    public static <T, filters> T createFromRegistry(final Class<T> serviceInterfaceClass, final RegistryClient registryClient, Router router, LoadBalancer loadBalance, Filter... filter) {

        // 加filte之一


        // 1. 简单：从zk拿到服务提供的列表
        List<ServiceProviderDesc> serviceProviderDescList =  registryClient.loadServiceProviderDescList(serviceInterfaceClass.getName());
        if(CollectionUtils.isEmpty(serviceProviderDescList)){
            log.error("no service provider for -> {}", serviceInterfaceClass);
            return null;
        }

        List<String> invokers = serviceProviderDescList.stream().map(e->e.httpUrl()).collect(Collectors.toList());
        // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）
        if(Objects.nonNull(router)){
            invokers = router.route(invokers);
        }

        // loadBalance
        String url;
        if(Objects.nonNull(loadBalance)){
            url = loadBalance.select(invokers);
        }else {
            url = invokers.get(0);
        }

        ServiceProviderDesc serviceProviderDesc = serviceProviderDescList.stream().filter(e->e.httpUrl().equals(url)).collect(Collectors.toList()).get(0);

        return (T) defaultProxy.create(serviceInterfaceClass, serviceProviderDesc, filter);

    }


    public static <T> T create(final Class<T> serviceClass, final ServiceProviderDesc serviceProviderDesc, Filter... filters) {

        // 0. 替换动态代理 -> AOP
        return (T) defaultProxy.create(serviceClass, serviceProviderDesc, filters);

    }


}
