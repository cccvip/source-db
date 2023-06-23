package com.cccvip.redis.store;

import lombok.Data;

import java.util.Map;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/18
 */
@Data
public class Dict {

    private Integer size;

    private Integer used;

    private Map<String, DictEntry> table;

    public Dict() {

    }

    public Dict(Integer size, Integer used, Map<String, DictEntry> table) {
        this.size = size;
        this.used = used;
        this.table = table;
    }


}


