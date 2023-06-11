package com.cccvip.socket.handler;

import com.cccvip.socket.command.Command;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @description: 命令行解析
 * @author：carl
 * @date: 2023/6/11
 */
public class CommandHandler extends SimpleChannelInboundHandler<Command> {


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command command) throws Exception {


    }

}
