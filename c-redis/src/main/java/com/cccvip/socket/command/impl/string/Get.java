package com.cccvip.socket.command.impl.string;

import com.cccvip.socket.command.Command;
import com.cccvip.socket.command.CommandType;
import com.cccvip.socket.resp.Resp;
import io.netty.channel.ChannelHandlerContext;

public class Get implements Command {

    @Override
    public CommandType type() {
        return CommandType.auth;
    }

    @Override
    public void setContent(Resp[] array) {


    }

    @Override
    public void handle(ChannelHandlerContext ctx) {

    }
}
