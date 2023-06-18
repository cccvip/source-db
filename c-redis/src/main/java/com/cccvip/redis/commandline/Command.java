package com.cccvip.redis.commandline;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public interface Command {

    CommandType type();

    void handle(ChannelHandlerContext ctx,ByteBuf byteBuf);



}
