package com.cccvip.socket.encoder;


import com.cccvip.redis.commandline.Command;
import com.cccvip.redis.commandline.CommandFactory;
import com.cccvip.redis.commandline.CommandUtils;
import com.cccvip.redis.resp.impl.RespType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


/**
 * RequestDecoder.
 * RESP协议输入接口请求解析
 * 因为输入的消息体不是定长,所以不能需要自己解析输入内容
 *
 * @author Carl, 2023-06-15 16:27
 */
@Slf4j
public class RequestDecoder extends SimpleChannelInboundHandler<ByteBuf> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf byteBuf) throws Exception {

        //根据byteBuf 判断当前命令是哪种类型
        Integer endIndex = CommandUtils.readEndIndex(byteBuf);
        if (endIndex == -1) {
            return;
        }

        //读取第一个首字节,就能判断是哪种类型
        byte c = byteBuf.readByte();
        RespType respType = RespType.findRespType(c);
        if (null == respType) {
            log.error("command is error");
            byteBuf.markReaderIndex();
            return;
        }
        byteBuf.markReaderIndex();

        Command command = CommandFactory.queryRespType(respType);

        command.handle(ctx, byteBuf);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getLocalizedMessage());
    }
}
