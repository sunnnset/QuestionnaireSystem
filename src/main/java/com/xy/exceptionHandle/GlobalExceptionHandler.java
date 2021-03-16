package com.xy.exceptionHandle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException.class)
    public MessageResponse handleGlobalException(Exception e) {
        if (e instanceof ServerException) {
            // 若发生了ServerException，则发送设定的错误码和错误消息
            log.warn(e.getMessage(), e);
            ServerException serverException = (ServerException) e;
            return new MessageResponse(serverException.getCode(), serverException.getMessage());
        } else {    // 若发生了不属于ServerException的异常，则发送500错误码以及该异常的message
            log.error(e.getMessage(), e);
            return new MessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }

    }
}
