/*
 * All Rights Reserved.
 *
 */
package com.cccvip.test;


import com.cccvip.redis.commandline.impl.string.StringCommandUtils;
import com.cccvip.redis.resp.Resp;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * SimpleString.
 *
 * @author Carl, 2023-06-16 13:24
 */
@Slf4j
public class SimpleString {

    private static final byte[] CRLF = "\r\n".getBytes(Resp.UTF_8);

    @Test
    public void TestContent() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeBytes("+Ping".getBytes(Resp.UTF_8));
        buffer.writeBytes(CRLF);

        String content = StringCommandUtils.getContent(buffer);
        log.info(content);
    }


    int getNumber(ByteBuf buffer) {
        char t;
        t = (char) buffer.readByte();
        int value = t - '0';
        return value;
    }

    @Test
    public void TestNumber() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeBytes("3\r\n$3\r\nset\r\n$4\r\nname\r\n$5\r\npdudo\r\n".getBytes(Resp.UTF_8));
        buffer.writeBytes(CRLF);
        int number = getNumber(buffer);
        log.info("test number {}", number);
    }


}
