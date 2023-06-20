/*
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.store;


import java.util.List;

/**
 * RedisMethod.
 *
 * @author Carl, 2023-06-20 13:31
 */
public interface RedisMethod {

    /**
     * keys. 获取所有key
     *
     * @author Carl
     */
    List<String> keys();

    /**
     * get. 根据String获取key
     *
     * @author Carl
     */
    DictEntry get(String key);

    /**
     * add. 添加元素
     *
     * @param key
     * @return long
     * @throw
     * @author Lee
     */
    long add(String key, DictEntry dictEntry);

    /**
     * remove. 异常指定的key
     *
     * @author Carl
     */
    long remove(List<String> keys);

    boolean exist(String key);

}

