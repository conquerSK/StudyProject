package com.dashang.education.module.login;

import com.dashang.education.mvp.base.BaseView;
import com.dashang.education.mvp.presenter.BasePresenter;

/**
 * Created by shenkai on 2018/5/28.
 * desc:
 */

public interface LoginContract {
    interface View extends BaseView {
        String getUserName();

        String getPassword();

        /**
         * 登录成功
         */
        void toMainActivity();

        /**
         * 登录失败
         */
        void showFailedError();
    }

    interface Presenter extends BasePresenter<View> {
        void login(String userName, String password);
    }

}
