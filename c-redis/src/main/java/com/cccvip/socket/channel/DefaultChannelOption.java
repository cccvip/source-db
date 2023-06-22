package com.cccvip.socket.channel;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 主要是考虑 epoll/select等关键函数实现,window下做开发,就暂时从简
 * @author：carl
 * @date: 2023/6/11
 */
public class DefaultChannelOption implements LocalChannelOption<NioServerSocketChannel> {

    private final NioEventLoopGroup boss;

    private final NioEventLoopGroup worker;

    public DefaultChannelOption() {

        this.boss = new NioEventLoopGroup(1, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Server_boss_" + index.getAndIncrement());
            }
        });

        this.worker = new NioEventLoopGroup(2, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Server_worker_" + index.getAndIncrement());
            }
        });
    }

    @Override
    public EventLoopGroup boss() {
        return boss;
    }

    @Override
    public EventLoopGroup selectors() {

        return worker;
    }

    @Override
    public Class<NioServerSocketChannel> getChannelClass() {

        return NioServerSocketChannel.class;
    }
}
