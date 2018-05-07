package com.fuzamei.demo.utils;

public enum ResponseCodeEnum {

    Success(0, "请求成功"),
    TOKEN_EXPIRED(99, "token过期");

    private int code;
    private String message;

    ResponseCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
