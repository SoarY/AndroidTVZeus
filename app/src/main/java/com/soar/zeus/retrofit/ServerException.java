package com.soar.zeus.retrofit;

/**
 * 服务器返回码__Android自定义异常对象
 */
public class ServerException extends RuntimeException {
    private int code;//服务器错误码
    private String msg;

    public ServerException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
