package com.qiaosong.arraignmentmeeting.http;

import android.text.TextUtils;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(HttpAddress.getServerAddress())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(20, TimeUnit.SECONDS);
        httpClient.writeTimeout(20, TimeUnit.SECONDS);
        httpClient.readTimeout(20, TimeUnit.SECONDS);
        httpClient.addNetworkInterceptor(new StethoInterceptor());
        httpClient.addInterceptor(interceptor.setLevel(HttpLoggingInterceptor.Level.BODY));
        if (baseUrl == null || TextUtils.isEmpty(baseUrl)) {
            builder.baseUrl(HttpAddress.getServerAddress()).client(httpClient.build());
        } else {
            builder.baseUrl(baseUrl).client(httpClient.build());
        }
        return builder.build().create(serviceClass);
    }

}
