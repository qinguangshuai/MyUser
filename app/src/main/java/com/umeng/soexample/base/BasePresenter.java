package com.umeng.soexample.base;

import android.content.Context;

/**
 * date:2018/11/17    9:29
 * author:秦广帅(Lenovo)
 * fileName:BasePresenter
 */
public class BasePresenter {

    private BaseView mBaseView;
    private Context mContext;

    public BasePresenter(Context context) {
        this.mContext = context;
    }

    public BaseView getBaseView() {
        return mBaseView;
    }

    public Context getContext() {
        return mContext;
    }

    public void attch(BaseView baseView) {
        this.mBaseView = baseView;
    }

    public void detach() {
        mContext=null;
        mBaseView=null;
    }
}
