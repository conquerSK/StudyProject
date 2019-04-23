package com.dashang.education.util;

import android.content.Context;
import android.os.Handler;

import com.dashang.education.data.base.BaseApplication;


/**
 * Created by shenkai on 2018/4/28.
 * desc:
 */

public class AppUtils {

    public static Context getContext() {
        return BaseApplication.getApplication();
    }

    /**
     * 运行在主线程
     *
     * @param r 运行的Runnable对象
     */
    public static void runOnUIThread(Runnable r) {
        if (isRunOnUIThread()) {
            // 已经是主线程, 直接运行
            r.run();
        } else {
            // 如果是子线程, 借助handler让其运行在主线程
            getHandler().post(r);
        }
    }

    /**
     * 判断是否运行在主线程
     *
     * @return true：当前线程运行在主线程
     * fasle：当前线程没有运行在主线程
     */
    public static boolean isRunOnUIThread() {
        // 获取当前线程id, 如果当前线程id和主线程id相同, 那么当前就是主线程
        int myTid = android.os.Process.myTid();
        if (myTid == getMainThreadId()) {
            return true;
        }
        return false;
    }

    public static Handler getHandler() {
        return BaseApplication.getHandler();
    }

    /**
     * 获取主线程id
     *
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return BaseApplication.getMainThreadId();
    }

    /**
     * 延迟执行任务
     * @param run 任务
     * @param time 延迟的时间
     */
    public static void postDelayed(Runnable run, int time) {
        BaseApplication.getHandler().postDelayed(run, time);
    }

    /**
     * 取消任务
     * @param autoRunTask
     */
    public static void cancel(Runnable autoRunTask) {
        BaseApplication.getHandler().removeCallbacks(autoRunTask);
    }

}
