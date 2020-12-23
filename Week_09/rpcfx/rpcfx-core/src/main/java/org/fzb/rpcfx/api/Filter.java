package org.fzb.rpcfx.api;

/**
 * Filter
 *
 * @author fengzhenbing
 */
public interface Filter {
    boolean filter(RpcfxRequest request);
}
