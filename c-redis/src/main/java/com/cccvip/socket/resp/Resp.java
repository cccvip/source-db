package com.cccvip.socket.resp;

import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public interface Resp {

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    void write(ByteBuf buffer);

}
