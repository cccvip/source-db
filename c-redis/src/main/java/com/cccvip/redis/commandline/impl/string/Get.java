package com.cccvip.redis.commandline.impl.string;

import com.cccvip.redis.commandline.BulkCommand;
import com.cccvip.redis.resp.RespType;
import com.cccvip.redis.resp.entity.BulkArray;
import com.cccvip.redis.store.DictEntry;
import com.cccvip.redis.store.RedisCore;
import com.cccvip.redis.store.RedisSingleton;
import com.cccvip.redis.store.impl.DictString;
import com.cccvip.socket.encoder.Response;
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
        RedisCore redisCore = RedisSingleton.getSingleton();
        DictEntry dictEntry = redisCore.get(key);
        if (dictEntry == null) {
            ctx.writeAndFlush(new Response(RespType.ERROR.getPrefix(), "error"));
            return;
        }

        if (dictEntry instanceof DictString) {
            String value = ((DictString) dictEntry).getValue();
            ctx.writeAndFlush(new Response(RespType.Simple.getPrefix(), value));
            return;
        }

        //抛出异常
        throw new UnsupportedOperationException();
    }
}
