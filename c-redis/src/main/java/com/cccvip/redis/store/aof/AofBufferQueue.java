package com.cccvip.redis.store.aof;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/23
 * 參考代碼 https://github.com/redis/redis/blob/2.2.6/src/aof.c
 */
public class AofBufferQueue extends LinkedBlockingQueue {
    //写buffer, buffer size

    //buffer 暂定1M
    private static final int BUFFER_SIZE = 1024;

    public AofBufferQueue() {
        super(BUFFER_SIZE);
    }


}
