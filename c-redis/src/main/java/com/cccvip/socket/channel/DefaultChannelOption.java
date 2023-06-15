package com.cccvip.socket.channel;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalServerChannel;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description: 主要是考虑 epoll/select等关键函数实现,window下做开发,就暂时从简
 * @author：carl
 * @date: 2023/6/11
 */
public class DefaultChannelOption implements LocalChannelOption {

    private final DefaultEventLoopGroup boss;

    private final DefaultEventLoopGroup selectors;

    public DefaultChannelOption() {

        this.boss = new DefaultEventLoopGroup(4, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Server_boss_" + index.getAndIncrement());
            }
        });

        this.selectors = new DefaultEventLoopGroup(8, new ThreadFactory() {
            private AtomicInteger index = new AtomicInteger(0);

            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "Server_selector_" + index.getAndIncrement());
            }
        });
    }

    @Override
    public EventLoopGroup boss() {
        return boss;
    }

    @Override
    public EventLoopGroup selectors() {

        return selectors;
    }

    @Override
    public Class getChannelClass() {

        return LocalServerChannel.class;
    }
}