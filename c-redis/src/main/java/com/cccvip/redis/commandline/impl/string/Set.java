package com.cccvip.redis.commandline.impl.string;

import com.cccvip.redis.commandline.BulkCommand;
import com.cccvip.redis.resp.RespType;
import com.cccvip.redis.resp.entity.BulkArray;
import com.cccvip.redis.resp.impl.SimpleString;
import com.cccvip.redis.store.RedisCore;
import com.cccvip.redis.store.RedisSingleton;
import com.cccvip.redis.store.impl.DictString;
import com.cccvip.socket.encoder.Response;
import io.netty.channel.ChannelHandlerContext;

import java.util.List;

/**
 * 完整的参考命令 http://doc.redisfans.com/string/set.html
 *
 * @author Carl
 * @return
 * @throw
 */
public class Set implements BulkCommand {

    private String key;

    private String value;

    private long timeout = -1;

    private boolean notExistSet = false;

    private boolean existSet = false;

    @Override
    public void setContent(List<BulkArray> array) {
        key = array.get(1).getCommand();
        value = array.get(2).getCommand();

        //兼容set key value EX/NX/PX等扩展
        for (int i = 3; i < array.size(); i++) {
            String cmd = array.get(i).toUtf8String();

            if ("EX".equals(cmd)) {
                i++;
                String seconds = array.get(i).toUtf8String();
                timeout = Integer.parseInt(seconds) * 1000;
            } else if ("PX".equals(cmd)) {
                i++;
                String seconds = array.get(i).toUtf8String();
                timeout = Integer.parseInt(seconds) * 1000;
            } else if ("NX".equals(cmd)) {
                notExistSet = true;
            }

        }

    }

    @Override
    public void handle(ChannelHandlerContext ctx) {

        RedisCore redisCore = RedisSingleton.getSingleton();

        boolean exist = redisCore.exist(key);

        if (notExistSet && exist) {
            ctx.writeAndFlush(new Response(RespType.ERROR.getPrefix(), null));
            return;
        }

        if (timeout != -1) {
            timeout += System.currentTimeMillis();
        }

        DictString stringData = new DictString();
        stringData.setValue(value);
        stringData.setTimeout(timeout);

        redisCore.add(key, stringData);
        ctx.writeAndFlush(new Response(RespType.Simple.getPrefix(), "OK"));
    }
}
