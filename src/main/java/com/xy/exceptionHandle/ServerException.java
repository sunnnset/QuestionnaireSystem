package com.xy.exceptionHandle;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServerException extends RuntimeException{
    private Integer code;

    public ServerException(MsgEnum msgEnum) {
        super(msgEnum.getMessage());
        this.code = msgEnum.getCode();
    }
}
