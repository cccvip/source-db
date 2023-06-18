package com.cccvip.redis.resp.impl;

import com.cccvip.redis.commandline.CommandType;
import com.cccvip.redis.commandline.CommandUtils;
import com.cccvip.redis.resp.Resp;
import io.netty.buffer.ByteBuf;
import lombok.Data;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/18
 */
@Data
public class SimpleString implements Resp {

    private String content;

    @Override
    public CommandType command() {

        return CommandType.ping;
    }

    @Override
    public void decode(ByteBuf byteBuf) {
        Integer endIndex = CommandUtils.readEndIndex(byteBuf);

        int startIndex = byteBuf.readerIndex();

        int size = endIndex - startIndex;

        byte[] bytes = new byte[size];
        byteBuf.readBytes(bytes);

        byteBuf.readerIndex(endIndex + 1);

        byteBuf.markReaderIndex();

        content = new String(bytes, Resp.UTF_8);
    }


}
