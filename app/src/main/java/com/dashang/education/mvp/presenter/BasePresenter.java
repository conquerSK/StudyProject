package com.dashang.education.mvp.presenter;

import com.dashang.education.mvp.base.BaseView;

/**
 * Created by shenkai on 2018/4/24.
 * desc: P层接口，提供视图的绑定和解绑方法
 */

public interface BasePresenter<V extends BaseView> {

    /**
     * 绑定视图
     * @param view
     */
    void attachView(V view);

    /**
     * 解除绑定
     */
    void detachView();
}
