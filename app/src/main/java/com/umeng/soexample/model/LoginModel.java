package com.umeng.soexample.model;

import android.os.AsyncTask;
import android.os.SystemClock;

import com.umeng.soexample.callback.LoginCallBack;

import javax.security.auth.callback.Callback;

/**
 * date:2018/11/16    17:33
 * author:秦广帅(Lenovo)
 * fileName:LoginModel
 */
public class LoginModel {
    public void login(final String name, final String sex, final LoginCallBack callback){
        new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                if(name.contains("111")&&sex.contains("111")){
                    return "成功";
                }else{
                    return "失败";
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s.contains("成功")){
                    callback.onLoginSuccess("成功");
                }else{
                    callback.onLoginFailer("失败");
                }
            }
        }.execute();
    }
}
