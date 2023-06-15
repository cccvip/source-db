package com.cccvip.socket.resp;


import io.netty.buffer.ByteBuf;

/**
 * RespUtils.
 *
 * @author Carl, 2023-06-15 16:04
 */
public abstract class RespUtils implements Resp {

    public abstract void prefix(ByteBuf buffer);

    /**
     * suffix 后缀默认使用 /r/n结尾
     *
     * @param buffer
     * @return void
     * @throw
     * @author Carl
     */
    void suffix(ByteBuf buffer) {
        buffer.writeByte(RespType.CR.getPrefix());
        buffer.writeByte(RespType.LF.getPrefix());
    }

    public void response(ByteBuf buffer) {
        prefix(buffer);

        write(buffer);

        suffix(buffer);
    }

}
