package com.dianian.casual.net;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 付博文 on 2017/8/29.
 */

public class AppClient {
    public Retrofit retrofit = null;
    public static AppClient sAppClient = null;
    private String API_SERVER_URL = "";

    public static AppClient getAppClient() {
        if (sAppClient == null) {
            synchronized (AppClient.class) {
                if ((sAppClient == null)) {
                    sAppClient = new AppClient();
                }
            }
        }
        return sAppClient;
    }

    protected AppClient() {

    }

    /**
     * 如要设置 log信息拦截器、缓存、公共参数、请求头、cookie 重写此类
     *
     * @return
     */
    public Retrofit retrofit() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            //设置超时
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);
            //以上设置结束，build()
            OkHttpClient okHttpClient = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(API_SERVER_URL)
                    //设置 Json 转换器
                    .addConverterFactory(GsonConverterFactory.create())
                    //RxJava 适配器
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }

    public void setURL(String url) {
        API_SERVER_URL = url;
    }


}
