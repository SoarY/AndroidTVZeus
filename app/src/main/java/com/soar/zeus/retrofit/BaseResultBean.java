package com.soar.zeus.retrofit;

/**
 * YONG_
 * 返回实体基类
 */
public class BaseResultBean<T> {

    public int errorCode;

    public String errorMsg;

    public T data;
}
