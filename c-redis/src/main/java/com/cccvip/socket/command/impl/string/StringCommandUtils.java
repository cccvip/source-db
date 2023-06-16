/*
 * Copyright @2023 CrisisGo Inc.
 * All Rights Reserved.
 *
 */
package com.cccvip.socket.command.impl.string;


import com.cccvip.socket.command.CommandUtils;
import com.cccvip.socket.resp.Resp;
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

        int size = endIndex - startIndex - 1;

        byte[] bytes = new byte[size];
        byteBuf.readBytes(bytes);

        byteBuf.readerIndex(endIndex + 1);

        byteBuf.markReaderIndex();

        String content = new String(bytes, Resp.UTF_8);

        return content;
    }


}
