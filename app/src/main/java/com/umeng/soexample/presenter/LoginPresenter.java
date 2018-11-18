package com.umeng.soexample.presenter;

import android.content.Context;
import android.text.TextUtils;

import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.callback.LoginCallBack;
import com.umeng.soexample.model.LoginModel;
import com.umeng.soexample.view.LoginView;

/**
 * date:2018/11/16    18:00
 * author:秦广帅(Lenovo)
 * fileName:LoginPresenter
 */
public class LoginPresenter extends BasePresenter {
    private LoginView mLoginView;
    private LoginModel mLoginModel;

    public LoginPresenter(Context context,LoginView loginView) {
        super(context);
        mLoginView = loginView;
        mLoginModel = new LoginModel();
    }

    public void login(String name,String sex){
        if(TextUtils.isEmpty(name)&&TextUtils.isEmpty(sex)){
            mLoginView.onKong();
        }
        mLoginModel.login(name, sex ,new LoginCallBack() {
            @Override
            public void onLoginSuccess(String result) {
                mLoginView.onSuccess(result);
            }

            @Override
            public void onLoginFailer(String msg) {
                mLoginView.onFailer(msg);
            }
        });
    }
}
