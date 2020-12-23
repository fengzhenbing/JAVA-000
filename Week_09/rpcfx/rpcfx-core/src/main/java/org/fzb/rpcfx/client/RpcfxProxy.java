package org.fzb.rpcfx.client;

import org.fzb.rpcfx.api.Filter;

/**
 * RpcfxProxy
 *
 * @author fengzhenbing
 */
public interface RpcfxProxy {
    <T> T create(final Class<T> serviceClass, final String url,  final Filter... filters);
}
