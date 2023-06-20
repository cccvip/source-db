/*
 * All Rights Reserved.
 *
 */
package com.cccvip.redis.resp.entity;


import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * BulkArray.
 *
 * @author Carl, 2023-06-20 9:23
 */
@Data
public class BulkArray {

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    private String command;

    public String toUtf8String() {
        return new String(command.getBytes(), CHARSET);
    }

}
