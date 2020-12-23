package org.fzb.rpcfx.client;

import org.fzb.rpcfx.api.Filter;

/**
 * BuddyProxy
 *
 * @author fengzhenbing
 */
public class BuddyProxy implements RpcfxProxy{
    @Override
    public <T> T create(Class<T> serviceClass, String url, Filter... filters) {
        return null;
    }
}
