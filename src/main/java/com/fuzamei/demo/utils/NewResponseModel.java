package com.fuzamei.demo.utils;

import java.io.Serializable;

public class NewResponseModel<T> implements Serializable {
    private static final long serialVersionUID = -4688208407174044501L;

    private int status;
    private String info;
    private T data;
    private long timeStamp = System.currentTimeMillis();

    public static <T> NewResponseModel<T> Success() {
        return new NewResponseModel<>(ResponseCodeEnum.Success.getCode(), ResponseCodeEnum.Success.getMessage());
    }


    public NewResponseModel(int status) {
        this.status = status;
    }

    public NewResponseModel(int status, String info) {
        this.info = info;
        this.status = status;
    }

    public NewResponseModel(ResponseCodeEnum responseCodeEnum) {
        this.info = responseCodeEnum.getMessage();
        this.status = responseCodeEnum.getCode();
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
