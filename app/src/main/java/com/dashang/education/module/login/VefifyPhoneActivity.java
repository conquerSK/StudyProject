package com.dashang.education.module.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.dashang.education.R;
import com.dashang.education.mvp.BaseActivity;
import com.dashang.education.util.StatusBarDarkModeUtil;

public class VefifyPhoneActivity extends BaseActivity {
    private static final String KEY_VERIFY_PHONE = "VERIFY_PHONE";
    private String phoneNumber;

    public static void start(Context context, String phoneNumber) {
        Intent starter = new Intent(context, VefifyPhoneActivity.class);
        starter.putExtra(KEY_VERIFY_PHONE, phoneNumber);
        context.startActivity(starter);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setStatusBarColor();//适配状态栏和标题栏颜色一致
        StatusBarDarkModeUtil.setStatusBarDarkMode(this);//适配状态栏文字颜色
        setTitle("验证手机");
        phoneNumber = getIntent().getStringExtra(KEY_VERIFY_PHONE);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_vefify_phone);
    }
}
