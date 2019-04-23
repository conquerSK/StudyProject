package com.dashang.education.module.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dashang.education.R;
import com.dashang.education.mvp.BaseMvpActivity;
import com.dashang.education.util.StatusBarDarkModeUtil;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * Created by shenkai on 2018/5/28.
 * desc:
 */

public class LoginActivity extends BaseMvpActivity<LoginContract.Presenter> implements LoginContract.View, View.OnClickListener {
    private EditText et_phone;
    private EditText et_pwd;
    private Button btn_login;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        StatusBarDarkModeUtil.setStatusBarDarkMode(this);//状态栏字体颜色暗色
        fullScreenHaveText();
        et_phone = findViewById(R.id.et_phone);
        et_pwd = findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
        
        InitialValueObservable<CharSequence> obPhone = RxTextView.textChanges(et_phone);
        InitialValueObservable<CharSequence> obPwd = RxTextView.textChanges(et_pwd);
        Observable.combineLatest(obPhone, obPwd, new BiFunction<CharSequence, CharSequence, Boolean>() {
            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2) throws Exception {
                return isPhoneValid() && isPasswordValid();
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                btn_login.setEnabled(aBoolean);
            }
        });

        findViewById(R.id.tv_forget_pwd).setOnClickListener(this);
        findViewById(R.id.login_by_code).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Glide.with(this).load("").into(new ImageView(this));
    }

    private boolean isPhoneValid() {
        Editable text = et_phone.getText();
        return !TextUtils.isEmpty(text) && text.length() == 11;
    }

    private boolean isPasswordValid() {
        Editable text = et_pwd.getText();
        if (TextUtils.isEmpty(text)) {
            return false;
        }
        return text.length() >= 8 && text.length() <= 12;
    }

    @Override
    protected LoginContract.Presenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_forget_pwd:
                startActivity(new Intent(this, VefifyPhoneActivity.class));
                break;
            case R.id.login_by_code:

                break;
        }
    }

    @Override
    public String getUserName() {
        return et_phone.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_pwd.getText().toString();
    }

    @Override
    public void toMainActivity() {

    }

    @Override
    public void showFailedError() {
        showToast("用户名或密码错误");
    }
}
