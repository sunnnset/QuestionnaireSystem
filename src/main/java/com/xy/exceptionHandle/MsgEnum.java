package com.xy.exceptionHandle;

import lombok.Getter;
import lombok.ToString;

@Getter
public enum MsgEnum {
    CODE_TEST(342536, "test"),
    SUCCESS(200, "操作成功"),

    INVALID_PARAM(444, "输入参数错误"),
    ITEM_NOT_FOUND(444, "未找到请求的数据"),

    TOKEN_NOT_FOUND(450, "未检测到Token"),
    TOKEN_INVALID(451, "Token错误"),
    TOKEN_EXPIRED(452, "Token已过期"),

    LOGIN_SUCCESS(200, "登录成功"),
    REGISTER_SUCCESS(200, "注册成功"),
    USER_NAME_EXIST(444, "用户名已存在"),

    EMPTY_FILE(444, "文件为空"),
    UPLOAD_FAILURE(444, "文件上传失败"),
    UPLOAD_SUCCESS(200, "文件上传成功");



    private Integer code;
    private String message;
    MsgEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
