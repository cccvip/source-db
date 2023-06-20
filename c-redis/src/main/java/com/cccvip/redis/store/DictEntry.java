package com.cccvip.redis.store;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/18
 */
public interface DictEntry {
    /**
     * timeout. 读取超时时间
     */
    long timeout();

    /**
     * setTimeout. 设置超时时间
     */
    void setTimeout(long timeout);

}
