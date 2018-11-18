package com.umeng.soexample.model;

import com.google.gson.Gson;
import com.umeng.soexample.bean.LoginUser;
import com.umeng.soexample.http.OkHttp;

import java.util.List;

/**
 * date:2018/11/16    19:05
 * author:秦广帅(Lenovo)
 * fileName:ToModel
 */
public class ToModel {
    public void two(String path, final OkHttp.HttpCallback callback){
        OkHttp okHttp = new OkHttp();
        okHttp.jie(path, new OkHttp.HttpCallback() {
            @Override
            public void setData(String s) {
                Gson gson = new Gson();
                LoginUser user = gson.fromJson(s, LoginUser.class);
                List<LoginUser.ResultsBean> results = user.getResults();
                callback.getData(results);
            }

            @Override
            public void getData(List<LoginUser.ResultsBean> list) {

            }
        });
    }
}
