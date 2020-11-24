package com.github.mushanwb.entity;


public class ResultResponse<T> {
    // http 状态码
    private int code;

    // 返回信息
    private String msg;

    // 返回的数据
    private T data;

    public static <T> ResultResponse<T> result(int code, String msg, T data) {
        ResultResponse<T> result = new ResultResponse<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public ResultResponse() {

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
