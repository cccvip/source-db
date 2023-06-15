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
 * @version CrisisGo v1.0
 */
public class StringCommand implements Command {

    @Override
    public CommandType type() {
        return null;
    }

    @Override
    public void handle(ChannelHandlerContext ctx, ByteBuf byteBuf) {

        Integer endIndex = CommandUtils.readEndIndex(byteBuf);


        //读取完整命令
        int startIndex = byteBuf.readerIndex();

        int size = endIndex - startIndex - 1;

        byte[] bytes = new byte[size];
        byteBuf.readBytes(bytes);

        byteBuf.readerIndex(endIndex + 1);

        byteBuf.markReaderIndex();

        String content = new String(bytes, Resp.UTF_8);

    }

}
