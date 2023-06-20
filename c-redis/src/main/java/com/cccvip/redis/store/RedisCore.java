/*
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.store;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * RedisCore.
 *
 * @author Carl, 2023-06-20 13:28
 */
@Data
public class RedisCore implements RedisMethod {

    private final Dict dict;

    public RedisCore() {
        dict = new Dict();
    }

    @Override
    public List<String> keys() {

        return new ArrayList<>(dict.getTable().keySet());
    }

    @Override
    public DictEntry get(String key) {

        return dict.getTable().get(key);
    }

    @Override
    public long add(String key, DictEntry dictEntry) {

        dict.getTable().put(key, dictEntry);

        return 1;
    }


    @Override
    public long remove(List<String> keys) {
        return 0;
    }

    @Override
    public boolean exist(String key) {
        return dict.getTable().containsKey(key);
    }
}
