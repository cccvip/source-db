package com.cccvip.redis.resp.impl;

import com.cccvip.redis.commandline.CommandType;
import com.cccvip.redis.resp.Resp;
import com.cccvip.redis.resp.RespType;
import com.cccvip.redis.resp.entity.BulkArray;
import io.netty.buffer.ByteBuf;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
@Slf4j
public class BulkResp implements Resp<List<BulkArray>> {

    private final List<BulkArray> bulkArrays;

    public BulkResp() {
        bulkArrays = new ArrayList<>();
    }

    @Override
    public CommandType command() {

        BulkArray commandArray = bulkArrays.get(0);

        String command = commandArray.getCommand();

        command = command.toLowerCase();

        return CommandType.valueOf(command);
    }

    @Override
    public List<BulkArray> getContent() {
        return bulkArrays;
    }

    @Override
    public void decode(ByteBuf byteBuf) {
        //标志位
        byte number = byteBuf.readByte();

        byte[] srtbyte = new byte[]{number};

        log.info("receive number {}", new String(srtbyte));

        //使用循环,不使用递归
        int loop = (int) number - '0';

        for (int s = 0; s < loop; s++) {
            bulkArrayDeal(bulkArrays, byteBuf);
        }

    }

    //  $3\r\n
    //  set\r\n
    //  $4\r\n
    //  name\r\n
    //  $5\r\n
    //  pdudo\r\n
    public void bulkArrayDeal(List<BulkArray> bulkArrays, ByteBuf byteBuf) {

        byte bc = byteBuf.readByte();

        while (bc == '\r' || bc == '\n') {
            bc = byteBuf.readByte();
        }

        if (RespType.BULK.getPrefix() == bc) {
            //需要读取的size
            int size = byteBuf.readByte() - '0';
            bulkArrays.add(readContent(size, byteBuf));
        }
    }

    //读取content
    BulkArray readContent(int size, ByteBuf in) {
        int start = 1;

        byte b;
        //跳过\r\n
        in.skipBytes(2);

        byte[] characters = new byte[size];

        while (start <= size && (b = in.readByte()) != '\r') {
            characters[start - 1] = b;
            start++;
        }

        BulkArray bulkArray = new BulkArray();

        bulkArray.setCommand(new String(characters));

        return bulkArray;
    }


}
