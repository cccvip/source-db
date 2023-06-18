package com.cccvip.redis.commandline;



import com.cccvip.redis.resp.impl.RespType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * CommandFacotry.
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

    //根据返回类型
    //例如输入 set 1 2
    //
    public static Command queryRespType(RespType respType) {
        CommandType commandType = respType.getCommandType();
        return commandType.getSupplier().get();
    }


}
