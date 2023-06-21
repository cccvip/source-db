package com.cccvip.socket.encoder;


import com.cccvip.redis.resp.RespType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class ResponseEncoder extends MessageToByteEncoder<Response> {

    public ResponseEncoder() {

    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Response resp, ByteBuf buffer) throws Exception {
        try {

            byte prefix = resp.getPrefix();
            String content = resp.getContent();

            buffer.writeByte(prefix);

            char[] charArray = content.toCharArray();
            for (char each : charArray) {
                buffer.writeByte((byte) each);
            }

            buffer.writeByte(RespType.R.getPrefix());
            buffer.writeByte(RespType.N.getPrefix());

        } catch (Exception e) {
            channelHandlerContext.close();
        }
    }

}
