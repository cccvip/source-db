package com.cccvip.redis.commandline.impl;

import com.cccvip.redis.commandline.Command;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * @description: ping->pong
 * @author：carl
 * @date: 2023/6/11
 */
public class Ping implements Command {

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf byteBuf) {

    }
}
