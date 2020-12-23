package org.fzb.rpcfx.client;

import org.fzb.rpcfx.api.RpcfxRequest;
import org.fzb.rpcfx.api.RpcfxResponse;

import java.io.IOException;

/**
 * RestClient
 *
 * @author fengzhenbing
 */
public interface NetClient {
    RpcfxResponse sendRequest(RpcfxRequest req) throws IOException;
}
