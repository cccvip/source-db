package com.cccvip.redis.commandline.impl;

import com.cccvip.redis.commandline.BulkCommand;
import com.cccvip.redis.commandline.Command;
import com.cccvip.redis.resp.RespType;
import com.cccvip.redis.resp.entity.BulkArray;
import com.cccvip.socket.encoder.Response;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * @description: ping->pong
 * @author：carl
 * @date: 2023/6/11
 */
public class Ping implements BulkCommand {

    @Override
    public void handle(ChannelHandlerContext ctx) {

        ctx.writeAndFlush(new Response(RespType.Simple.getPrefix(),"Pong"));

    }

    @Override
    public void setContent(List<BulkArray> array) {

    }
}
