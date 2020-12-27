package org.fzb.rpcfx.demo.provider.rpcfx;

import org.fzb.rpcfx.api.Filter;
import org.fzb.rpcfx.api.RpcfxRequest;
import org.springframework.stereotype.Component;

/**
 * HeaderFilter
 *
 * @author fengzhenbing
 */
@Component
public class HeaderFilter implements Filter {
    @Override
    public boolean filter(RpcfxRequest request) {
        request.getHeaders().put("token","fengzhenbing");
        return true;
    }
}
