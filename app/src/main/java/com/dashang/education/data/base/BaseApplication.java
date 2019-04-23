package com.dashang.education.data.base;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.Nullable;

import com.dashang.education.BuildConfig;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;


/**
 * Created by shenkai on 2018/4/28.
 * desc:
 */

public class BaseApplication extends Application {
    private static BaseApplication sApplication;
    private static Handler mHandler;
    protected static int mainThreadId;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        sApplication = this;
        mHandler = new Handler();
        mainThreadId = Process.myTid();
        initLogger();
    }

    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(2)         // (Optional) How many method line to show. Default 2
                .methodOffset(5)        // (Optional) Hides internal method calls up to offset. Default 5
                .tag("LOGGER")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){
            @Override
            public boolean isLoggable(int priority, @Nullable String tag) {
                return BuildConfig.LOG_DEBUG;
            }
        });
    }

    /**
     * 获取上下文对象
     * @return context
     */
    public static Context getApplication() {
        return sApplication;
    }

    /**
     * 获取全局handler
     * @return 全局handler
     */
    public static Handler getHandler() {
        return mHandler;
    }

    /**
     * 获取主线程id
     * @return 主线程id
     */
    public static int getMainThreadId() {
        return mainThreadId;
    }
}
