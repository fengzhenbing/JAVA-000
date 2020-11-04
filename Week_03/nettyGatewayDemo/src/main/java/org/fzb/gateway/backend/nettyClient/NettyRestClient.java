package org.fzb.gateway.backend.nettyClient;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.fzb.gateway.backend.IRestClient;
import org.fzb.gateway.filter.HttpFilterChain;
import org.fzb.gateway.loadbalancer.ILoadBalancer;
import org.fzb.gateway.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description
 * @Author fzb
 * @date 2020.11.03 19:57
 */
public class NettyRestClient  implements IRestClient {

    private Logger logger = LoggerFactory.getLogger(NettyRestClient.class);

    private HttpFilterChain responseFilterChain;

    @Override
    public void request(ChannelHandlerContext ctx, FullHttpRequest request, ILoadBalancer loadBalancer) {
        //负载均衡
        Server backendServer = loadBalancer.chooseServer(request);
        String url =backendServer.getScheme()+"://"+ backendServer.getHost()+":"+backendServer.getPort()+request.uri();
        if(logger.isDebugEnabled()){
            logger.debug("请求后端服务地址为:\n{}",url);
        }

        //1)
        EventLoopGroup loopGroup = new NioEventLoopGroup();
        try{
            //2)构造器
            Bootstrap b = new  Bootstrap();
            //3)配置
            b.group(loopGroup)
                    //.option(ChannelOption.SO_KEEPALIVE, true)
                    .option(ChannelOption.TCP_NODELAY, true)
                     .handler(new LoggingHandler(LogLevel.INFO))
                    .channel(NioSocketChannel.class)
                    .handler(new NettyRestClientInitializer(ctx,responseFilterChain));



          //4) 连接后端服务
            Channel ch = b.connect(backendServer.getHost(),backendServer.getPort()).sync().channel();

            //5）构造请求
            ch.writeAndFlush(request.copy());

            //6）等待服务端关闭
            ch.closeFuture().sync();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //7)释放
            loopGroup.shutdownGracefully();
        }


    }

    @Override
    public void registerResponseChain(HttpFilterChain responseFilterChain) {
        this.responseFilterChain = responseFilterChain;
    }

   /* public static void main(String[] args) {
        FullHttpRequest request = new DefaultFullHttpRequest(
                HttpVersion.HTTP_1_1, HttpMethod.GET, "/json");
        request.headers().set(HttpHeaderNames.HOST, "localhost");
        request.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.CLOSE);
        request.headers().set(HttpHeaderNames.ACCEPT_ENCODING, HttpHeaderValues.GZIP);

         new NettyRestClient().request(null,request);
    }*/

}
