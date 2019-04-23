package com.dashang.education.data.manager;

import com.dashang.education.data.helper.RetrofitHelper;

import io.reactivex.Observable;

/**
 * Created by shenkai on 2018/4/25.
 * desc: 通过RxJava操作符，返回Observable对象给Presenter使用
 */

public class DataManager {

    public Observable getSearchBooks(String name) {
        return RetrofitHelper.getInstance().getBookApi().getSearchBooks(name);
    }

}
