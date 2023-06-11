package com.cccvip.socket.resp;

import io.netty.buffer.ByteBuf;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
public interface Resp {
    static void write(Resp resp, ByteBuf buffer) {
        
    }

}
