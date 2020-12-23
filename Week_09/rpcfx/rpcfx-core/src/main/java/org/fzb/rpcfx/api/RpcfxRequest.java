package org.fzb.rpcfx.api;

import lombok.Data;

import java.util.*;

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
    private Map<String,String> headers = new LinkedHashMap();
}
