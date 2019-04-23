package com.dashang.education.common.rx;

import android.content.Context;

import com.dashang.education.common.exception.BaseException;

import io.reactivex.observers.DefaultObserver;

/**
 * Created by shenkai on 2018/4/25.
 * desc:
 */

public abstract class ErrorHandlerObserver<T> extends DefaultObserver<T> {
    private RxErrorHandler mErrorHandler;
    private Context mContext;

    public ErrorHandlerObserver(RxErrorHandler errorHandler, Context context) {
        mErrorHandler = errorHandler;
        mContext = context;
    }

    @Override
    public void onError(Throwable e) {
        BaseException exception = mErrorHandler.handleError(e);
        mErrorHandler.showErrorMessage(exception);
    }
}
