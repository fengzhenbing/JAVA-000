package org.fzb.gateway.outbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpUtil;
import org.fzb.gateway.filter.HttpFilterChain;
import org.fzb.gateway.inbound.HttpInboundHandler;
import org.fzb.gateway.inbound.HttpInboundInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author fzb
 * @date 2020.11.03 16:41
 */
public class HttpOutboundHandler extends ChannelOutboundHandlerAdapter {
    private Logger logger = LoggerFactory.getLogger(HttpOutboundHandler.class);

    private HttpFilterChain httpFilterChain;

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //1）调用响应过滤链
        if (httpFilterChain != null && msg instanceof FullHttpResponse) {
            httpFilterChain.invoke((FullHttpResponse) msg, ctx);
        }

        //2）写出到前端客户端
        if(logger.isDebugEnabled()){
            ByteBuf data = (ByteBuf) msg;
            byte[] bytes = new byte[data.readableBytes()];
            data.getBytes(0, bytes);
            logger.debug("后端服务响应:\n{}", new String(bytes, "UTF-8"));
        }

        ctx.write(msg, promise);
    }

    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void read(ChannelHandlerContext ctx) throws Exception {
        ctx.read();
    }

    public HttpOutboundHandler httpFilterChain(HttpFilterChain filterChain) {
        this.httpFilterChain = filterChain;
        return this;
    }
}
