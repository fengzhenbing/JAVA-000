package org.fzb.rpcfx.demo.provider.rpcfx;

import org.fzb.rpcfx.api.Router;

import java.util.List;

/**
 * PathRouter
 *
 * @author fengzhenbing
 */
public class PathRouter implements Router {
    @Override
    public List<String> route(List<String> urls) {
        //router todo
        return urls;
    }
}
