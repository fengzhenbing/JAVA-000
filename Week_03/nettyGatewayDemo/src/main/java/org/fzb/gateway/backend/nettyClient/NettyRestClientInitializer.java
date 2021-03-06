package org.fzb.gateway.backend.nettyClient;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpContentDecompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import org.fzb.gateway.filter.HttpFilterChain;

/**
 * @Description
 * @Author fzb
 * @date 2020.11.03 20:37
 */
public class NettyRestClientInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 网关服务端的上下文
     */
    private ChannelHandlerContext fontCtx;

    /**
     * 响应调用链
     */
    private HttpFilterChain responseFilterChain;

    public NettyRestClientInitializer(ChannelHandlerContext frontCtx, HttpFilterChain responseFilterChain) {
        this.fontCtx = frontCtx;
        this.responseFilterChain = responseFilterChain;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();


        //客户端http解码
        p.addLast(new HttpClientCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));

        //反压缩
        p.addLast(new HttpContentDecompressor());

        //解析结果
        p.addLast(new NettyRestClientHandler(fontCtx, responseFilterChain));


    }

}
