package com.dashang.education.common.rx;

import android.content.Context;
import android.widget.Toast;

import com.dashang.education.common.exception.BaseException;
import com.dashang.education.common.exception.ErrorMessageFactory;

import retrofit2.HttpException;

/**
 * Created by shenkai on 2018/4/26.
 * desc:
 */

public class RxErrorHandler {
    private Context mContext;

    public RxErrorHandler(Context context) {
        mContext = context;
    }

    public BaseException handleError(Throwable e) {
        BaseException exception = new BaseException();
        if (e instanceof HttpException) {
            exception.setCode(((HttpException) e).code());
        } else {
            exception.setCode(BaseException.UNKNOWN_ERROR);
        }
        exception.setShowMessage(ErrorMessageFactory.create(mContext, exception.getCode()));
        return exception;
    }

    public void showErrorMessage(BaseException e) {
        Toast.makeText(mContext, e.getShowMessage(), Toast.LENGTH_SHORT).show();
    }
}
