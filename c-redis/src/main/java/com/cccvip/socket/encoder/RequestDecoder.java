package com.cccvip.socket.encoder;


import com.cccvip.redis.commandline.Command;
import com.cccvip.redis.commandline.CommandFactory;
import com.cccvip.redis.commandline.CommandUtils;
import com.cccvip.redis.commandline.impl.string.StringCommandUtils;
import com.cccvip.redis.resp.entity.Errors;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
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
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        //根据byteBuf判断  如果没有\r\n 结束符,命令不完整
        Integer endIndex = CommandUtils.readEndIndex(in);
        if (endIndex == -1) {
            throw new Exception("error command");
        }
        String c = in.toString(CharsetUtil.UTF_8);
        log.info("receive content {}", c);

        int mark = in.readerIndex();
        try {
            Command command = CommandFactory.queryRespType(in);

            if (command == null) {
                String content = StringCommandUtils.getContent(in);
                ctx.writeAndFlush(new Errors("unsupport command:" + content));
                return;
            }
            command.handle(ctx);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //还原
            in.readerIndex(mark);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getLocalizedMessage());
    }
}
