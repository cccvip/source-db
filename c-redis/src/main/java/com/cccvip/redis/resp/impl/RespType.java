package com.cccvip.redis.resp.impl;

import com.cccvip.redis.commandline.CommandType;

/**
 * @description:
 * @author：carl
 * @date: 2023/6/18
 */
public enum RespType {


    SimpleString((byte)'+',CommandType.stringC)
    ;

    private byte prefix;

    private CommandType commandType;

    RespType(byte prefix, CommandType commandType) {
        this.prefix = prefix;
        this.commandType = commandType;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public byte getPrefix() {
        return prefix;
    }

    public void setPrefix(byte prefix) {
        this.prefix = prefix;
    }

    public static RespType findRespType(byte c) {
        for(RespType r:  RespType.values()){
            if(r.getPrefix()==c){
                return r;
            }
        };
        return null;
    }

}
