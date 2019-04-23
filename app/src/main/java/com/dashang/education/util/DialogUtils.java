package com.dashang.education.util;

import android.content.Context;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;

import com.dashang.education.ui.widget.CustomDialog;

public class DialogUtils {
    /**
     * 获取一个带两个按钮的对话框
     *
     * @param title   对话框标题
     * @param message 对话框提示内容
     */
    public static CustomDialog getAppDialog(Context context, CharSequence title, CharSequence message) {
        CustomDialog dialog = null;
        if (TextUtils.isEmpty(title)) {
            dialog = new CustomDialog(context, message);
        } else {
            dialog = new CustomDialog(context, title, message);
        }

        return dialog;
    }
}
