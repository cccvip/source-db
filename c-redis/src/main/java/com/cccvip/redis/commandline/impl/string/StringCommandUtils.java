/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.commandline.impl.string;


import com.cccvip.redis.commandline.CommandUtils;
import com.cccvip.redis.resp.impl.Resp;
import io.netty.buffer.ByteBuf;

/**
 * StringCommadnUtils.
 *
 * @author Carl, 2023-06-16 13:26
 * @version CrisisGo v1.0
 */
public class StringCommandUtils {

    public static String getContent(ByteBuf byteBuf) {
        //读取一个完整命令行
        Integer endIndex = CommandUtils.readEndIndex(byteBuf);

        int startIndex = byteBuf.readerIndex();

        int size = endIndex - startIndex;

        byte[] bytes = new byte[size];
        byteBuf.readBytes(bytes);

        byteBuf.readerIndex(endIndex + 1);

        byteBuf.markReaderIndex();

        String content = new String(bytes, Resp.UTF_8);

        return content;
    }

}
