package org.fzb.rpcfx.annotation;

import org.fzb.rpcfx.config.*;

/**
 * RpcfxConfigConfiguration
 *
 * @author fengzhenbing
 */
@EnableRpcfxConfigBindings({
        @EnableRpcfxConfigBinding(prefix = "dubbo.application", type = ApplicationConfig.class),
        @EnableRpcfxConfigBinding(prefix = "dubbo.registry", type = RegistryConfig.class),
        @EnableRpcfxConfigBinding(prefix = "dubbo.provider", type = ProviderConfig.class),
        @EnableRpcfxConfigBinding(prefix = "dubbo.consumer", type = ConsumerConfig.class),
        @EnableRpcfxConfigBinding(prefix = "dubbo.config-center", type = ConfigCenterConfig.class)
})
public class RpcfxConfigConfiguration {
}
