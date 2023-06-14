package com.cccvip.socket.command;

import com.cccvip.socket.command.impl.Auth;
import com.cccvip.socket.command.impl.string.Get;
import com.cccvip.socket.command.impl.Ping;
import com.cccvip.socket.command.impl.string.Set;

import java.util.function.Supplier;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public enum CommandType {
    /**
     * 验证授权
     */
    auth(Auth::new),
    /**
     * ping命令 测试服务是否正常
     */
    ping(Ping::new),
    /**
     * 字符串set命令 set one 2
     */
    set(Set::new),
    /**
     * 字符串get命令 get one == 2
     */
    get(Get::new)

    ;

    private final Supplier<Command> supplier;

    CommandType(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier<Command> getSupplier() {
        return supplier;
    }

}
