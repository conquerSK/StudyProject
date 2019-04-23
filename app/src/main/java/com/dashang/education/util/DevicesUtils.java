package com.dashang.education.util;

import android.util.DisplayMetrics;

/**
 * Created by shenkai on 2018/6/7.
 * desc:
 */

public class DevicesUtils {
    public static int getDeviceWidth() {
        DisplayMetrics dm = AppUtils.getContext().getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getDeviceHeight() {
        DisplayMetrics dm = AppUtils.getContext().getResources().getDisplayMetrics();
        return dm.heightPixels;
    }

    public static float getDensity() {
        DisplayMetrics dm = AppUtils.getContext().getResources().getDisplayMetrics();
        return dm.density;
    }
}
