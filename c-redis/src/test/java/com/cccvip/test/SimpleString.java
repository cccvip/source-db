/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package com.cccvip.test;


import com.cccvip.redis.commandline.impl.string.StringCommandUtils;
import com.cccvip.redis.resp.impl.Resp;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * SimpleString.
 * 
 * @author Carl, 2023-06-16 13:24
 */
@Slf4j
public class SimpleString {

    private static final byte[] CRLF = "\r\n".getBytes(Resp.UTF_8);

    @Test
    public void TestContent(){
       ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
       buffer.writeBytes("+Ping".getBytes(Resp.UTF_8));
       buffer.writeBytes(CRLF);

       String content =  StringCommandUtils.getContent(buffer);
       log.info(content);
    }


}
