package org.fzb.rpcfx.demo.provider.rpcfx;

import org.fzb.rpcfx.api.Filter;
import org.fzb.rpcfx.api.RpcfxRequest;

/**
 * HeaderFilter
 *
 * @author fengzhenbing
 */
public class HeaderFilter implements Filter {
    @Override
    public boolean filter(RpcfxRequest request) {
        request.getHeaders().put("token","fengzhenbing");
        return true;
    }
}
