package com.cccvip.socket.resp.impl;

import com.cccvip.socket.resp.RespType;
import com.cccvip.socket.resp.RespUtils;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
@Data
@AllArgsConstructor
public class SimpleString extends RespUtils {

    public static final SimpleString OK = new SimpleString("OK");

    private final String content;

    @Override
    public void write(ByteBuf buffer) {
        char[] contentArray = content.toCharArray();
        for (char c : contentArray) {
            buffer.writeByte(c);
        }
    }

    @Override
    public void prefix(ByteBuf buffer) {
        buffer.writeByte(RespType.Single.getPrefix());
    }

}
