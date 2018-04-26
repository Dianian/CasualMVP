package com.dianian.casualmvp;

import android.util.Log;

import com.dianian.casual.BasePresenter;

import java.io.IOException;

import okhttp3.ResponseBody;
import rx.Subscriber;

import static com.dianian.casualmvp.BaseApplication.sApiService;

public class MainPresenter extends BasePresenter<MainVIewInterface> {
    private MainVIewInterface mMainVIewInterface;

    public MainPresenter(MainVIewInterface mainVIewInterface) {
        mMainVIewInterface = mainVIewInterface;
    }

    public void http() {
        addSubscription(sApiService.Test("290"), new Subscriber<ResponseBody>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                try {
                    Log.e("JSON", "onNext: " + responseBody.string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
