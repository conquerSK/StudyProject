package com.dashang.education.common.exception;

import android.content.Context;

import com.dashang.education.R;

/**
 * Created by shenkai on 2018/4/26.
 * desc:
 */

public class ErrorMessageFactory {
    public static String create(Context context, int code) {
        String errorMsg = null;
        switch (code) {
            case BaseException.NETWORD_ERROR:
                errorMsg = context.getResources().getString(R.string.error_http);
                break;
            default:
                errorMsg = context.getResources().getString(R.string.error_unkown);
                break;
        }
        return errorMsg;
    }
}
