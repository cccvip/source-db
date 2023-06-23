/*
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.store;


import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RedisCore.
 *
 * @author Carl, 2023-06-20 13:28
 */
@Data
public class RedisCore implements RedisMethod {

    private final Dict dict;

    public RedisCore() {

        Map<String, DictEntry> table = new HashMap<>();

        dict = new Dict();

        dict.setTable(table);
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
