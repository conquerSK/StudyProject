package com.dashang.education.common.exception;

/**
 * Created by shenkai on 2018/4/26.
 * desc:
 */

public class BaseException extends RuntimeException{
    /*网络错误*/
    public static final int NETWORD_ERROR = 0x1;

    /*未知错误*/
    public static final int UNKNOWN_ERROR = 0x4;

    private int code;
    private String showMessage;

    public BaseException() {
    }

    public BaseException(int code, String showMessage) {
        this.code = code;
        this.showMessage = showMessage;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getShowMessage() {
        return showMessage;
    }

    public void setShowMessage(String showMessage) {
        this.showMessage = showMessage;
    }
}
