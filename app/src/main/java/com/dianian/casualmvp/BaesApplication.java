package com.dianian.casualmvp;

import android.app.Application;


import com.dianian.casual.net.AppClient;

public class BaesApplication extends Application {
    public static ApiService sApiService;

    @Override
    public void onCreate() {
        super.onCreate();
        AppClient.getAppClient().setURL("https://api-m.mtime.cn/PageSubArea/");
        sApiService = AppClient.getAppClient().retrofit().create(ApiService.class);
    }

}
