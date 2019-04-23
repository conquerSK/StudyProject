package com.dashang.education.util;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.dashang.education.data.base.BaseApplication;

/**
 * Created by shenkai on 2018/5/2.
 * desc:
 */

public class UiUtils {
    public static Resources getResources() {
        return BaseApplication.getApplication().getResources();
    }

    /** dip转换px */
    public static int dip2px(int dip) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /** px转换dip */

    public static int px2dip(int px) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static Drawable getDrawalbe(int id) {
        return getResources().getDrawable(id);
    }

    public static int getDimens(int homePictureHeight) {
        return (int) getResources().getDimension(homePictureHeight);
    }
}
