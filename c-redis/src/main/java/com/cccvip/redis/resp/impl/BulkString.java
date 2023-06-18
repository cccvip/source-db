package com.cccvip.redis.resp.impl;

import com.cccvip.redis.commandline.CommandType;
import com.cccvip.redis.resp.Resp;
import io.netty.buffer.ByteBuf;

/**
 * @description: set name pdudo
 * *3\r\n
 * $3\r\n
 * set\r\n
 * $4\r\n
 * name\r\n
 * $5\r\n
 * pdudo\r\n
 * @author：carl
 * @date: 2023/6/18
 */
public class BulkString implements Resp {

    private String command;

    @Override
    public CommandType command() {

        return null;
    }

    @Override
    public void decode(ByteBuf byteBuf) {
        //标志位
        int mark = byteBuf.readerIndex();
        byte number = byteBuf.readByte();
        byteBuf.readByte();

        try {

        } catch (Exception e) {

        } finally {
            //还原
            byteBuf.readerIndex(mark);
        }

    }


}
