package com.cccvip.socket.channel;

import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public interface LocalChannelOption<T extends Channel> {
    /**
     * @return 返回获取tcp线程
     */
    EventLoopGroup boss();

    /**
     * @return 返回处理tcp线程
     */
    EventLoopGroup selectors();

    /**
     * @return 返回管道类型
     */
    Class<? extends T> getChannelClass();

}
