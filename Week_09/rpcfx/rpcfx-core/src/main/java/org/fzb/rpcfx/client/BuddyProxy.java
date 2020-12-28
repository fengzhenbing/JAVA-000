package org.fzb.rpcfx.client;

import org.fzb.rpcfx.api.Filter;
import org.fzb.rpcfx.api.ServiceProviderDesc;

/**
 * BuddyProxy
 *
 * @author fengzhenbing
 */
public class BuddyProxy implements RpcfxProxy{
    @Override
    public <T> T create(Class<T> serviceClass, ServiceProviderDesc serviceProviderDesc, Filter... filters) {
        return null;
    }
}
