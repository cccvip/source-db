/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.commandline.impl.string;


import com.cccvip.redis.commandline.Command;
import com.cccvip.redis.commandline.CommandType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

/**
 * StringCommand.
 *
 * @author Carl, 2023-06-15 17:43
 */
public class StringCommand implements Command {

    @Override
    public CommandType type() {
        return CommandType.stringC;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf byteBuf) {
        String content = StringCommandUtils.getContent(byteBuf);
        String realContent = content.substring(1);


    }

}
