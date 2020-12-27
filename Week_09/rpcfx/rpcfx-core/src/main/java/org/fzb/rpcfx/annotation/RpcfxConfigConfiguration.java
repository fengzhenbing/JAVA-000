package org.fzb.rpcfx.annotation;

import org.fzb.rpcfx.config.*;

/**
 * RpcfxConfigConfiguration
 *
 * @author fengzhenbing
 */
@EnableRpcfxConfigBindings({
        @EnableRpcfxConfigBinding(prefix = ApplicationConfig.RPCFX +"."+ApplicationConfig.PREFIX, type = ApplicationConfig.class),
        @EnableRpcfxConfigBinding(prefix = RegistryConfig.RPCFX +"."+RegistryConfig.PREFIX, type = RegistryConfig.class),
        @EnableRpcfxConfigBinding(prefix = ProviderConfig.RPCFX +"."+ProviderConfig.PREFIX, type = ProviderConfig.class),
        @EnableRpcfxConfigBinding(prefix = ConsumerConfig.RPCFX +"."+ConsumerConfig.PREFIX, type = ConsumerConfig.class),
        @EnableRpcfxConfigBinding(prefix = ConfigCenterConfig.RPCFX +"."+ConfigCenterConfig.PREFIX, type = ConfigCenterConfig.class)
})
public class RpcfxConfigConfiguration {
}
