package com.cccvip.socket.command;

import com.cccvip.socket.resp.Resp;
import io.netty.channel.ChannelHandlerContext;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public interface Command {

    CommandType type();

    void setContent(Resp[] array);

    void handle(ChannelHandlerContext ctx);

}
