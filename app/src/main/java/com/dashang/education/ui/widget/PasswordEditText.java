package com.dashang.education.ui.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.dashang.education.R;

/**
 * 类名：PasswordEditText
 * 作者 ：ase
 * 主要功能：密码输入框，本组件默认实现了点击右侧图标切换图标状态和密码是否可见。
 * 设置的图标需为selector类型，并通过选中状态切换其显示的图片
 */

public class PasswordEditText extends AppCompatEditText {
    private Drawable eyeDrawable;

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        eyeDrawable = getCompoundDrawables()[2];//查看是否设置了右侧图标
        if (eyeDrawable == null) {
            eyeDrawable = getResources().getDrawable(R.drawable.ic_password_visibility);
            eyeDrawable.setBounds(0, 0, eyeDrawable.getIntrinsicWidth(), eyeDrawable.getIntrinsicHeight());
            setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], eyeDrawable, getCompoundDrawables()[3]);
            setSelected(false);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        if (selected) {
            setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            int eyeRight = getWidth() - getPaddingLeft();
            int eyeLeft = getWidth() - getTotalPaddingLeft();
            if (event.getX() >= eyeLeft && event.getX() <= eyeRight) {
                setSelected(!isSelected());
                Editable text = getText();
                if (!TextUtils.isEmpty(text)) {
                    Selection.setSelection(text, text.length());
                }
                return true;
            }
        }
        return super.onTouchEvent(event);
    }
}
