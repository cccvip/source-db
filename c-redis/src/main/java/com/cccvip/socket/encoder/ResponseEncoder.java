package com.cccvip.socket.encoder;


import com.cccvip.redis.resp.Resp;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ResponseEncoder extends MessageToByteEncoder<Resp> {

    public ResponseEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Resp resp, ByteBuf byteBuf) throws Exception {
        try {

//            respUtils.response(byteBuf);
            byteBuf.writeBytes(byteBuf);
        } catch (Exception e) {
            channelHandlerContext.close();
        }
    }

}
