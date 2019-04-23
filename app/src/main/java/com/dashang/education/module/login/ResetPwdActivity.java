package com.dashang.education.module.login;

import android.os.Bundle;

import com.dashang.education.R;
import com.dashang.education.mvp.BaseActivity;

/**
 * Created by shenkai on 2018/6/1.
 * desc:
 */

public class ResetPwdActivity extends BaseActivity {
    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_reset_pwd);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("重置密码");
    }

    @Override
    protected void initData() {

    }
}
