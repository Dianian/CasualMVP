package com.dianian.casualmvp;

import com.dianian.casual.net.AppClient;

import retrofit2.Retrofit;

public class BaseAppClient extends AppClient {

    @Override
    public Retrofit retrofit() {
        return super.retrofit();
    }

    @Override
    public void setURL(String url) {
        super.setURL(url);
    }
}
