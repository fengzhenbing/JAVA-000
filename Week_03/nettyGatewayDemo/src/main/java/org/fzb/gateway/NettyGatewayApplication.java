package org.fzb.gateway;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import org.fzb.gateway.filter.*;
import org.fzb.gateway.inbound.HttpInboundServer;

/**
 * @Description 启动测试类
 * @Author fzb
 * @date 2020.11.03 10:34
 */
public class NettyGatewayApplication {
    public static void main(String[] args) throws InterruptedException {

        //配置请求过滤链
        HttpFilterChain requestFilterChain = new BaseHttpFilterChain();
        requestFilterChain.addLast(new RequestFilterOne());
        requestFilterChain.addLast(new RequestFilterTwo());

        //配置响应过滤链
        HttpFilterChain responseFilterChain = new BaseHttpFilterChain();
        responseFilterChain.addLast(new ResponseFilterOne());
        responseFilterChain.addLast(new ResponseFilterTwo());

        //运行
        new HttpInboundServer().filterChain(requestFilterChain,responseFilterChain).run();
    }


    static class RequestFilterOne extends AbstractHttpRequestFilter {
        @Override
        protected void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, HttpFilterContext httpFilterContext) {
            logger.info("requestFilter 1");
            fullHttpRequest.headers().set("nio", "fengzhenbing");
            httpFilterContext.invoke(fullHttpRequest, ctx);
        }
    }

    static class RequestFilterTwo extends AbstractHttpRequestFilter {
        @Override
        protected void filter(FullHttpRequest fullHttpRequest, ChannelHandlerContext ctx, HttpFilterContext httpFilterContext) {
            logger.info("requestFilter 2");
            httpFilterContext.invoke(fullHttpRequest, ctx);
        }
    }

    static class ResponseFilterOne extends AbstractHttpResponseFilter {

        @Override
        protected void filter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx, HttpFilterContext httpFilterContext) {
            logger.info("responseFilter 1");
            httpFilterContext.invoke(fullHttpResponse, ctx);
        }
    }

    static class ResponseFilterTwo extends AbstractHttpResponseFilter {

        @Override
        protected void filter(FullHttpResponse fullHttpResponse, ChannelHandlerContext ctx, HttpFilterContext httpFilterContext) {
            logger.info("responseFilter 2");
            httpFilterContext.invoke(fullHttpResponse, ctx);
        }
    }

}
