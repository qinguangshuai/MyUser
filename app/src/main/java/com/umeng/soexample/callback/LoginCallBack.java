package com.umeng.soexample.callback;

/**
 * date:2018/11/16    17:28
 * author:秦广帅(Lenovo)
 * fileName:LoginCallBack
 */
public interface LoginCallBack {
    void onLoginSuccess(String result);
    void onLoginFailer(String msg);
}
