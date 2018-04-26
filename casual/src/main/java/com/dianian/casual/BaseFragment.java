package com.dianian.casual;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

public abstract class BaseFragment<V, T extends BasePresenter<V>> extends Fragment {
    protected Bundle mBundle = new Bundle();
    protected T mPresenter;
    protected Activity mActivity;

    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView((V) getActivity());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
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
        Intent mIntent = new Intent(mActivity, mClass);
        if (null != mBundle) {
            mIntent.putExtras(mBundle);
        }
        mActivity.startActivity(mIntent);
        mActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
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
        Intent mIntent = new Intent(mActivity, mClass);
        if (mBundle != null) {
            mIntent.putExtras(mBundle);
        }
        mActivity.startActivityForResult(mIntent, requestCode);
        mActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    protected abstract T createPresenter();
}
