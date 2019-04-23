package com.dashang.education.ui.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by shenkai on 2018/5/22.
 * desc:
 */

public class CustomDialog extends Dialog {
    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.width = 0;
        attributes.height = 0;
        window.setAttributes(attributes);
    }
}
