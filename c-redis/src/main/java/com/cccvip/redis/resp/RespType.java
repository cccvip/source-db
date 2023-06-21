package com.cccvip.redis.resp;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/18
 */
public enum RespType {

    ERROR((byte) '-'),
    Simple((byte) '+'),
    BULK((byte) '$'),
    INTEGER((byte) ':'),
    MULTYBULK((byte) '*'),
    R((byte) '\r'),
    N((byte) '\n'),
    ;

    private byte prefix;

    RespType(byte prefix) {
        this.prefix = prefix;
    }

    public byte getPrefix() {
        return prefix;
    }

    public void setPrefix(byte prefix) {
        this.prefix = prefix;
    }

    public static RespType findRespType(byte c) {
        for (RespType r : RespType.values()) {
            if (r.getPrefix() == c) {
                return r;
            }
        }
        ;
        return null;
    }

}
