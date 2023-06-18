package com.cccvip.redis.commandline;

import com.cccvip.redis.commandline.impl.Ping;
import com.cccvip.redis.commandline.impl.string.Get;
import com.cccvip.redis.commandline.impl.string.Set;

import java.util.function.Supplier;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public enum CommandType {

    get(Get::new), set(Set::new), ping(Ping::new);

    private final Supplier<Command> supplier;

    CommandType(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier<Command> getSupplier() {
        return supplier;
    }

}
