package com.dashang.education.common.exception;

/**
 * Created by shenkai on 2018/4/26.
 * desc:
 */

public class ApiException extends BaseException {
    public ApiException(int code, String showMessage) {
        super(code, showMessage);
    }
}
