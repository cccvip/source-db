package com.cccvip.socket.netty;

import com.cccvip.socket.channel.DefaultChannelOption;
import com.cccvip.socket.channel.LocalChannelOption;
import com.cccvip.socket.encoder.RequestDecoder;
import com.cccvip.socket.encoder.ResponseEncoder;
import com.cccvip.socket.util.PropertiesUtil;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
@Slf4j
public class NettyServer {

    ServerBootstrap serverBootstrap = new ServerBootstrap();

    LocalChannelOption channelOption = new DefaultChannelOption();

    private final EventExecutorGroup redisSingleEventExecutor;

    public NettyServer() {
        this.redisSingleEventExecutor = new NioEventLoopGroup(1);
    }

    public void start() {

        serverBootstrap.group(channelOption.boss(), channelOption.selectors())
                .channel(channelOption.getChannelClass())
                .handler(new LoggingHandler(LogLevel.INFO))
                //队列大小
                .option(ChannelOption.SO_BACKLOG, 1024)
                //
                .option(ChannelOption.SO_REUSEADDR, true)
                //
                .option(ChannelOption.SO_KEEPALIVE, PropertiesUtil.getTcpKeepAlive())
                .localAddress(new InetSocketAddress(PropertiesUtil.getNodeAddress(), PropertiesUtil.getNodePort()))
                //业务流程处理
                //1 心跳
                //2 命令处理
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {

                        ChannelPipeline channelPipeline = socketChannel.pipeline();

                        channelPipeline.addLast(
                                //输出
                                new ResponseEncoder(),
                                //请求
                                new RequestDecoder()
                        );

                    }
                });

        ChannelFuture sync = null;

        try {
            sync = serverBootstrap.bind().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info(sync.channel().localAddress().toString());
    }


    public void close() {

        try {
            channelOption.boss().shutdownGracefully();
            channelOption.selectors().shutdownGracefully();
            redisSingleEventExecutor.shutdownGracefully();
        } catch (Exception ignored) {
            log.warn("Exception!", ignored);
        }

    }

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.start();
    }

}
