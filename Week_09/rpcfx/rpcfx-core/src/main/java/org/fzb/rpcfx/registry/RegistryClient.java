package org.fzb.rpcfx.registry;

import lombok.extern.slf4j.Slf4j;
import org.fzb.rpcfx.annotation.ServiceRegisterPostProcessor;
import org.fzb.rpcfx.api.ServiceProviderDesc;
import org.fzb.rpcfx.config.RegistryConfig;
import org.springframework.beans.BeansException;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;

/**
 * RegistryClient
 *
 * @author fengzhenbing
 */
public interface RegistryClient {

    /**
     * 服务信息注册
     *
     * @param serviceProviderDesc
     */
    void registerService(ServiceProviderDesc serviceProviderDesc);

    /**
     * 服务信息获取
     *
     * @return
     */
    List<ServiceProviderDesc> loadServiceProviderDescList(String className);
}
