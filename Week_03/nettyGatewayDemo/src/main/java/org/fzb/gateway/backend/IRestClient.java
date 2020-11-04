package org.fzb.gateway.backend;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.fzb.gateway.filter.HttpFilterChain;
import org.fzb.gateway.loadbalancer.ILoadBalancer;

public interface IRestClient {
    void request(ChannelHandlerContext ctx, FullHttpRequest request, ILoadBalancer loadBalancer);

    void registerResponseChain(HttpFilterChain responseFilterChain);
}
