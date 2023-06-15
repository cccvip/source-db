package com.cccvip.socket.resp;

import com.cccvip.socket.command.CommandType;

/**
 * @author carl
 */
public enum RespType {
    Single((byte) '+', CommandType.stringC),

    Error((byte) '-', null),

    Number((byte) '.', null),

    Bulk((byte) '$', null),

    Array((byte) '*', null),

    CR((byte) '\r', null),

    LF((byte) '\n', null);

    RespType(byte prefix, CommandType commandType) {
        this.prefix = prefix;
        this.commandType = commandType;
    }

    private byte prefix;

    private CommandType commandType;

    public byte getPrefix() {
        return prefix;
    }

    public void setPrefix(byte prefix) {
        this.prefix = prefix;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public static RespType findRespType(byte c) {
        for (RespType respType : RespType.values()) {
            if (c == respType.prefix) {
                return respType;
            }
        }
        return null;
    }

}
