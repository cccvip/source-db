/*
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.store;


/**
 * RedisSingleton.
 *
 * @author Carl, 2023-06-20 14:31
 */
public class RedisSingleton {

    private static volatile RedisCore redisCore = new RedisCore();

    public static RedisCore getSingleton() {
        return redisCore;
    }

}
