package com.cccvip.redis.store.aof;

import com.cccvip.redis.store.RedisCore;
import com.cccvip.redis.store.RedisSingleton;
import com.cccvip.socket.util.PropertiesUtil;

import java.io.File;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/23
 */
public class Aof {

    private AofBufferQueue aofBufferQueue = new AofBufferQueue();

    private static final String suffix = ".aof";

    private String fileName = PropertiesUtil.getAofPath();

    private RedisCore redisCore;

    public Aof() {
        redisCore = RedisSingleton.getSingleton();
        File file = new File(this.fileName + suffix);
        if (!file.isDirectory()) {
            File parentFile = file.getParentFile();
            if (null != parentFile && !parentFile.exists()) {
                parentFile.mkdirs(); // 创建文件夹
            }
        }

//        start();
    }


}
