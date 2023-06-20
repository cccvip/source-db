package com.cccvip.redis.resp;

import com.cccvip.redis.commandline.CommandType;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/18
 */
public interface Resp<T> {
    Charset UTF_8 = StandardCharsets.UTF_8;

    CommandType command();

    T getContent();

    void decode(ByteBuf byteBuf);
}
