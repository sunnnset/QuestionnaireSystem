package com.xy.exceptionHandle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    private Integer status;
    private String msg;
    private Map<String, Object> data;

    public MessageResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public MessageResponse(MsgEnum msgEnum){
      this.status = msgEnum.getCode();
      this.msg = msgEnum.getMessage();
    }

    public MessageResponse(MsgEnum msgEnum, Map<String, Object> data){
        this.status = msgEnum.getCode();
        this.msg = msgEnum.getMessage();
        this.data = data;
    }

}
