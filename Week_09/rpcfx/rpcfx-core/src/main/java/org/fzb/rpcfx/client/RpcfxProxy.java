package org.fzb.rpcfx.client;

import org.fzb.rpcfx.api.Filter;
import org.fzb.rpcfx.api.ServiceProviderDesc;

/**
 * RpcfxProxy
 *
 * @author fengzhenbing
 */
public interface RpcfxProxy {
    <T> T create(final Class<T> serviceClass, final ServiceProviderDesc serviceProviderDesc, final Filter... filters);
}
