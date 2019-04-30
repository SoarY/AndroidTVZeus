package com.meixun.videosearch.retrofit;

/**
 * 统一分发回传ui Exception
 */
public class APIException extends Exception {
    private int code;
    private String displayMessage;

    public APIException() {}

    public APIException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }

    public void setDisplayMessage(String displayMessage) {
        this.displayMessage = displayMessage;
    }

    public String getDisplayMessage() {
        return displayMessage;
    }

    public int getCode() {
        return code;
    }
}
