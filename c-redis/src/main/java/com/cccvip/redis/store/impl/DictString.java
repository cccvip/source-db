/*
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.store.impl;


import com.cccvip.redis.store.DictEntry;

/**
 * DictString.
 *
 * @author Carl, 2023-06-20 13:49
 */
public class DictString implements DictEntry {

    private long timeout;

    private String value;

    public DictString() {
    }

    public DictString(String value) {
        this.value = value;
        this.timeout = -1;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public long timeout() {
        return timeout;
    }

    @Override
    public void setTimeout(long time) {
        timeout = time;
    }

}
