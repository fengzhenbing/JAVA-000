package org.fzb.rpcfx.api;

import lombok.Data;

/**
 * RpcfxResponse
 *
 * @author fengzhenbing
 */
@Data
public class RpcfxResponse {
    private Object result;
    private boolean status;
    private Exception exception;
}

