package com.cccvip.redis.commandline.impl.string;

import com.cccvip.redis.commandline.BulkCommand;
import com.cccvip.redis.resp.entity.BulkArray;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

public class Get implements BulkCommand {

    private String key;

    @Override
    public void setContent(List<BulkArray> array) {

        key = array.get(1).getCommand();
    }

    @Override
    public void handle(ChannelHandlerContext ctx) {


    }
}
