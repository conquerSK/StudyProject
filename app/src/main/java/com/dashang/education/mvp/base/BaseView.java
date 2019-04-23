package com.dashang.education.mvp.base;

/**
 * Created by shenkai on 2018/4/24.
 * desc:
 */

public interface BaseView {
    void showToast(String msg);

    void showProgress();

    void dismissProgress();
}
