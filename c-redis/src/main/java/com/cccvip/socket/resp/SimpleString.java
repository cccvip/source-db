package com.cccvip.socket.resp;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/11
 */
@Data
@AllArgsConstructor
public class SimpleString {
    public static final SimpleString OK = new SimpleString("OK");
    private final String content;

}
