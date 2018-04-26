package com.dianian.casual;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
/**
 * Created by 付博文 on 2017/8/28.
 */
public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected Bundle mBundle = new Bundle();
    protected T mPresenter;
    private static final String TAG = "BaseActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
        AppManager.getAppManager().addActivity(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//隐藏软键盘
        Log.i(TAG, "onCreate");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        mPresenter.datachView();
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 打开Activity
     *
     * @param mClass
     */
    protected void openActivity(Class<?> mClass) {
        Log.i(TAG, "openActivity");
        openActivity(mClass, null);
    }

    /**
     * 打开Activity 携带数据
     *
     * @param mClass
     * @param mBundle
     */
    protected void openActivity(Class<?> mClass, Bundle mBundle) {
        Log.i(TAG, "openActivity");
        Intent mIntent = new Intent(this, mClass);
        if (null != mBundle) {
            mIntent.putExtras(mBundle);
        }
        startActivity(mIntent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 打开Activity 有回调
     *
     * @param mClass
     * @param requestCode
     */
    protected void openActivityForResult(Class<?> mClass, int requestCode) {
        Log.i(TAG, "openActivityForResult");
        openActivityForResult(mClass, requestCode, null);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    /**
     * 打开ctivity 有回调 携带数据
     *
     * @param mClass
     * @param requestCode
     * @param mBundle
     */
    protected void openActivityForResult(Class<?> mClass, int requestCode, Bundle mBundle) {
        Log.i(TAG, "openActivityForResult");
        Intent mIntent = new Intent(this, mClass);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        startActivityForResult(mIntent, requestCode);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        Log.i(TAG, "finish");
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

//    /**
//     * Rx点击事件防抖动
//     *
//     * @param view
//     * @param action1
//     */
//    protected void setClick(View view, Action1<Void> action1) {
//        RxView.clicks(view).throttleFirst(1, TimeUnit.SECONDS).subscribe(action1);
//    }


    protected abstract T createPresenter();
}
