package com.dashang.education.data.helper;

import android.util.Log;

import com.dashang.education.BuildConfig;
import com.dashang.education.data.api.BookApi;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shenkai on 2018/4/25.
 * desc:
 */

public class RetrofitHelper {
    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int TIMEOUT_READ = 20;
    private static final int TIMEOUT_CONNECTION = 10;

    private Retrofit mRetrofit;
    private HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
        @Override
        public void log(String message) {
            Log.i("RetrofitLog:", "retrofitBack=" + message);
        }
    }).setLevel(HttpLoggingInterceptor.Level.BODY);

    private RetrofitHelper() {
        initRetrofit();
    }

    private void initRetrofit(/*, String url*/) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getOkhttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private OkHttpClient getOkhttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //打印日志
                .addInterceptor(mLoggingInterceptor)
                //time out
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                //失败重连
                .retryOnConnectionFailure(true)
                .build();
        return okHttpClient;
    }

    public static RetrofitHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public BookApi getBookApi() {
        return mRetrofit.create(BookApi.class);
    }

    private static class SingletonHolder {
        private static final RetrofitHelper INSTANCE = new RetrofitHelper();
    }

    /**
     * 自己定义的日志拦截器
     */
    private class LogInteceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Logger.i("RetrofitHelper" + String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            // log Response Body
            if(BuildConfig.DEBUG) {
                String responseBody = response.body().string();
                Logger.i("RetrofitHelper", String.format("Received response for %s in %.1fms%n%s%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers(), responseBody));
                return response.newBuilder()
                        .body(ResponseBody.create(response.body().contentType(), responseBody))
                        .build();
            } else {
                Logger.i("RetrofitHelper", String.format("Received response for %s in %.1fms%n%s",
                        response.request().url(), (t2 - t1) / 1e6d, response.headers()));
                return response;
            }
        }
    }
}
