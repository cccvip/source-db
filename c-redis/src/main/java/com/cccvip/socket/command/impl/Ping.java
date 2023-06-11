package com.cccvip.socket.command.impl;

import com.cccvip.socket.command.Command;
import com.cccvip.socket.command.CommandType;
import com.cccvip.socket.resp.Resp;
import com.cccvip.socket.resp.SimpleString;
import io.netty.channel.ChannelHandlerContext;

/**
 * @description: ping->pong
 * @author：carl
 * @date: 2023/6/11
 */
public class Ping implements Command {
    @Override
    public CommandType type() {
        return CommandType.ping;
    }

    @Override
    public void setContent(Resp[] array) {

    }

    @Override
    public void handle(ChannelHandlerContext ctx) {
        ctx.write(new SimpleString("PONG"));
        ctx.flush();
    }
}
