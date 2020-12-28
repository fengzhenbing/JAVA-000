package org.fzb.rpcfx.client;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.fzb.rpcfx.api.RpcfxRequest;
import org.fzb.rpcfx.api.RpcfxResponse;

import java.io.IOException;

/**
 * OkHttpClient
 *
 * @author fengzhenbing
 */
@Slf4j
public class OkHttpClient implements NetClient{
    public static final MediaType JSONTYPE = MediaType.get("application/json; charset=utf-8");

    @Override
    public RpcfxResponse sendRequest(RpcfxRequest req) throws IOException {
        String reqJson = JSON.toJSONString(req);
        if(log.isDebugEnabled()){
            log.debug("req json: {}",reqJson);
        }
        // 1.可以复用client
        // 2.尝试使用httpclient或者netty client
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        final Request request = new Request.Builder()
                .url(req.getUrl())
                .post(RequestBody.create(JSONTYPE, reqJson))
                .build();
        String respJson = client.newCall(request).execute().body().string();
         if(log.isDebugEnabled()){
            log.debug("resp json: {}",respJson);
        }
        return JSON.parseObject(respJson, RpcfxResponse.class);
    }
}
