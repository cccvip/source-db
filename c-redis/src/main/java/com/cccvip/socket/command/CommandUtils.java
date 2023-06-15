package com.cccvip.socket.command;


import io.netty.buffer.ByteBuf;
import io.netty.util.ByteProcessor;

/**
 * CommandUtils.
 *
 * @author Carl, 2023-06-15 17:26
 */
public class CommandUtils {

    public static Integer readEndIndex(ByteBuf byteBuf) {
        //读取换行符号 \n
        int endIndex = byteBuf.forEachByte(ByteProcessor.FIND_LF);
        //读取 \r
        endIndex = (endIndex > 0 && byteBuf.getByte(endIndex - 1) == '\r') ? endIndex : -1;
        //当前命令是有明确结束符号的
        return endIndex;
    }


}
