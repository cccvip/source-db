package com.cccvip.redis.commandline.impl.string;

import com.cccvip.redis.commandline.BulkCommand;
import com.cccvip.redis.resp.entity.BulkArray;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class Set implements BulkCommand {

    @Override
    public void setContent(List<BulkArray> array) {

    }

    @Override
    public void handle(ChannelHandlerContext ctx) {

    }
}
