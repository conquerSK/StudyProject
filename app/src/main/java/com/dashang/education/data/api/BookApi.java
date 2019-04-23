package com.dashang.education.data.api;

import com.dashang.education.data.bean.Book;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by shenkai on 2018/4/25.
 * desc:
 */

public interface BookApi {


    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name);
}
