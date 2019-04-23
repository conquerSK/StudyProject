package com.dashang.education.module.login.verificationcode;

import android.os.Bundle;

import com.dashang.education.R;
import com.dashang.education.mvp.BaseMvpActivity;

/**
 * Created by shenkai on 2018/5/31.
 * desc:
 */

public class LoginByVerificationCodeActivity extends BaseMvpActivity<VerificationCodeContract.Presenter>
        implements VerificationCodeContract.View{

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_login_by_code);
    }

    @Override
    protected VerificationCodeContract.Presenter initPresenter() {
        return new VerificationCodePresenter();
    }
}
