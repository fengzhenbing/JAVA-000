package org.fzb.rpcfx.client;

import com.alibaba.fastjson.parser.ParserConfig;
import org.fzb.rpcfx.api.Filter;
import org.fzb.rpcfx.api.LoadBalancer;
import org.fzb.rpcfx.api.Router;

import java.util.ArrayList;
import java.util.List;

/**
 * Rpcfx
 *
 * @author fengzhenbing
 */
public class Rpcfx {
    static {
        ParserConfig.getGlobalInstance().addAccept("io.kimmking");
    }

    private static final RpcfxProxy defaultProxy = new JdkProxy();

    public static <T, filters> T createFromRegistry(final Class<T> serviceClass, final String zkUrl, Router router, LoadBalancer loadBalance, Filter filter) {

        // 加filte之一

        // curator Provider list from zk
        List<String> invokers = new ArrayList<String>();
        // 1. 简单：从zk拿到服务提供的列表
        // 2. 挑战：监听zk的临时节点，根据事件更新这个list（注意，需要做个全局map保持每个服务的提供者List）

        List<String> urls = router.route(invokers);

        String url = loadBalance.select(urls); // router, loadbalance

        return (T) defaultProxy.create(serviceClass, url, filter);

    }


    public static <T> T create(final Class<T> serviceClass, final String url, Filter... filters) {

        // 0. 替换动态代理 -> AOP
        return (T) defaultProxy.create(serviceClass, url, filters);

    }

}
