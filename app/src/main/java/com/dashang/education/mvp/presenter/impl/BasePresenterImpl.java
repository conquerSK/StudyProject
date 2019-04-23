package com.dashang.education.mvp.presenter.impl;

import com.dashang.education.common.manager.RxManager;
import com.dashang.education.mvp.base.BaseView;
import com.dashang.education.mvp.presenter.BasePresenter;

import java.lang.ref.WeakReference;

/**
 * Created by shenkai on 2018/4/24.
 * desc:
 */

public class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {
    private WeakReference<V> weakView;
    private RxManager mRxManager = new RxManager();

    protected V getView() {
        return weakView.get();
    }

    /**
     * 用于检查View是否为空对象
     *
     * @return
     */
    protected boolean isViewAttached() {
        return weakView != null && weakView.get() != null;
    }

    @Override
    public void attachView(V view) {
        weakView = new WeakReference<V>(view);
    }

    @Override
    public void detachView() {
        mRxManager.unSubscribe();
        if (weakView != null) {
            weakView.clear();
            weakView = null;
        }
    }
}
