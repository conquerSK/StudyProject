package com.dashang.education.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.dashang.education.R;

import java.util.Locale;

/**
 * Created by shenkai on 2018/5/31.
 * desc: EditText和圆角矩形按钮的组合控件
 */

public class EditTextWithRoundRect extends FrameLayout implements TextWatcher {
    private static final long MILLISINFUTURE = 60 * 1000;
    private static final long COUNTDOWNINTERVAL = 1000;
    private static final String FORMAT = "剩余%dS";
    private final LayoutInflater mLayoutInflater;
    private EditText edit_number;
    private TextView tv_get_code;

    private CountDownTimer mCountDownTimer = new CountDownTimer(MILLISINFUTURE, COUNTDOWNINTERVAL) {
        @Override
        public void onTick(long millisUntilFinished) {
            if (tv_get_code != null) {
                tv_get_code.setEnabled(false);
                tv_get_code.setText(String.format(Locale.CHINA, FORMAT, millisUntilFinished / 1000));
            }
        }

        @Override
        public void onFinish() {
            if (tv_get_code != null) {
                tv_get_code.setEnabled(true);
                tv_get_code.setText(getResources().getString(R.string.login_get_code));
            }
        }
    };

    public EditTextWithRoundRect(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mLayoutInflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        mLayoutInflater.inflate(R.layout.layout_edittext_with_round_rect, this, true);
        edit_number = findViewById(R.id.edit_number);
        tv_get_code = findViewById(R.id.tv_get_code);
        edit_number.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        tv_get_code.setEnabled((!TextUtils.isEmpty(s) || s.length() >= 11));
    }

    public EditText getEditText() {
        return edit_number;
    }

    public TextView getTextView() {
        return tv_get_code;
    }

    public CountDownTimer getCountDownTimer() {
        return mCountDownTimer;
    }

    public void setEditTextDrawableLeft(Drawable left) {
        if (left == null) {
            throw new NullPointerException("图片不能传递空");
        }
        edit_number.setCompoundDrawables(left, null, null, null);
    }
}
