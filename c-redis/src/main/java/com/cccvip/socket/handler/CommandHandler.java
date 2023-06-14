package com.cccvip.socket.handler;

import com.cccvip.socket.command.Command;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 命令行解析
 * @author：carl
 * @date: 2023/6/11
 */
@Slf4j
public class CommandHandler extends SimpleChannelInboundHandler<Command> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Command command) throws Exception {
        log.info("本次处理请求command{}",command.type());
        command.handle(channelHandlerContext);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getLocalizedMessage());
    }
}
