package com.cccvip.redis.commandline;

import com.cccvip.redis.commandline.impl.string.StringCommand;

import java.util.function.Supplier;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public enum CommandType {

    stringC(StringCommand::new)
    ;

    private final Supplier<Command> supplier;

    CommandType(Supplier supplier) {
        this.supplier = supplier;
    }

    public Supplier<Command> getSupplier() {
        return supplier;
    }

}
