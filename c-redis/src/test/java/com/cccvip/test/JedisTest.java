package com.cccvip.test;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/22
 */
@Slf4j
public class JedisTest {
    /**
     * 测试Ping 命令 收到的消息体内容是
     * *1
     * $4
     * PING
     * @param args
     */
    public static void main(String[] args) {
        //创建连接对象
        Jedis jedis = new Jedis("127.0.0.1", 6379);

        String pong = jedis.ping();

        log.info("get redis result {}", pong);
        //关闭连接
        jedis.close();
    }


}
