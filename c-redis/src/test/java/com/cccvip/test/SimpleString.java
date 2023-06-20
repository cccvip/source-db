/*
 * All Rights Reserved.
 *
 */
package com.cccvip.test;


import com.cccvip.redis.commandline.impl.string.StringCommandUtils;
import com.cccvip.redis.resp.Resp;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * SimpleString.
 *
 * @author Carl, 2023-06-16 13:24
 */
@Slf4j
public class SimpleString {

    private static final byte[] CRLF = "\r\n".getBytes(Resp.UTF_8);

    @Test
    public void TestContent() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeBytes("+Ping".getBytes(Resp.UTF_8));
        buffer.writeBytes(CRLF);

        String content = StringCommandUtils.getContent(buffer);
        log.info(content);
    }


    int getNumber(ByteBuf buffer) {
        char t;
        t = (char) buffer.readByte();
        int value = t - '0';
        return value;
    }

    @Test
    public void TestNumber() {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        buffer.writeBytes("3\r\n$3\r\nset\r\n$4\r\nname\r\n$5\r\npdudo\r\n".getBytes(Resp.UTF_8));
        buffer.writeBytes(CRLF);
        int number = getNumber(buffer);
        log.info("test number {}", number);
    }

    @Test
    public void TestList() {
        // 创建一个动态数组
        ArrayList<String> sites = new ArrayList<>();

        sites.add("Google");
        sites.add("Runoob");
        sites.add("Taobao");

        System.out.println("ArrayList 1: " + sites);

        // 创建另一个动态数组
        ArrayList<String> sites2 = new ArrayList<>();

        // 往动态数组中添加元素
        sites2.add("Wiki");
        sites2.add("Runoob");
        sites2.add("Google");
        System.out.println("ArrayList 2: " + sites2);

        // 保留元素
        sites.retainAll(sites2);
        System.out.println("保留的元素: " + sites);
    }


    @Test
    public void updateTime(){

        LocalDate localDate = LocalDate.now();



    }




}
