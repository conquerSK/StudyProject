package com.dashang.education.data.helper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by shenkai on 2018/4/25.
 * desc:
 */

public class RxHelper {
    /**
     * 统一线程处理
     * @param <T>
     * @return
     * 发布事件IO线程，接收事件主线程
     */
    public static <T> ObservableTransformer rxSchedulerHelper() {//compose处理线程
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


}
