package org.fzb.rpcfx.api;

import lombok.Data;

/**
 * RpcfxRequest
 *
 * @author fengzhenbing
 */
@Data
public class RpcfxRequest {
    private String serviceClass;
    private String method;
    private Object[] params;
    private String url;
}
