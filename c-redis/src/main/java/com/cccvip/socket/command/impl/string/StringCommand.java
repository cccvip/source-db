/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package com.cccvip.socket.command.impl.string;


import com.cccvip.socket.command.Command;
import com.cccvip.socket.command.CommandType;
import com.cccvip.socket.command.CommandUtils;
import com.cccvip.socket.resp.Resp;
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




    }

}
