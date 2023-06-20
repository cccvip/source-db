package com.cccvip.redis.commandline;


import com.cccvip.redis.resp.Resp;
import com.cccvip.redis.resp.RespType;
import com.cccvip.redis.resp.entity.BulkArray;
import com.cccvip.redis.resp.impl.BulkResp;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * CommandFactory.
 *
 * @author Carl, 2023-06-15 17:19
 */
public class CommandFactory {

    public static Map<String, Supplier<Command>> factoryMap = new HashMap<>();

    static {

        for (CommandType commandType : CommandType.values()) {
            factoryMap.put(commandType.name(), commandType.getSupplier());
        }
    }

    public static Command queryRespType(ByteBuf in) {

        byte header = in.readByte();
        CommandType commandType = null;
        if (header == RespType.MULTYBULK.getPrefix()) {

            Resp<List<BulkArray>> resp = new BulkResp();

            resp.decode(in);

            commandType = resp.command();

            BulkCommand command = (BulkCommand) commandType.getSupplier().get();

            command.setContent(resp.getContent());

            return command;
        }


        return null;
    }


}
