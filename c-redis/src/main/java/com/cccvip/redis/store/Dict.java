package com.cccvip.redis.store;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/18
 */
@Data
public class Dict {

    private Integer size;

    private Integer used;

    private List<DictEntry> table;

}
