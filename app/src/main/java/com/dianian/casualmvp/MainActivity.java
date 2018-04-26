package com.dianian.casualmvp;

import android.os.Bundle;

import com.dianian.casual.BaseActivity;

public class MainActivity extends BaseActivity<MainVIewInterface, MainPresenter> implements MainVIewInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.http();

    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }
}
