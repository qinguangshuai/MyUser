package com.umeng.soexample.presenter;

import android.content.Context;

import com.umeng.soexample.base.BaseModel;
import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.bean.LoginUser;
import com.umeng.soexample.http.OkHttp;
import com.umeng.soexample.model.ToModel;
import com.umeng.soexample.view.TwoView;

import java.util.List;

/**
 * date:2018/11/16    19:10
 * author:秦广帅(Lenovo)
 * fileName:TwoPresenter
 */
public class TwoPresenter {
    private TwoView mTwoView;
    private ToModel mToModel;
    private List<LoginUser.ResultsBean> mList;

    public TwoPresenter(TwoView twoView) {
        mTwoView = twoView;
        mToModel = new ToModel();
    }

    public void two(String path){
        mToModel.two(path, new OkHttp.HttpCallback() {
            @Override
            public void setData(String s) {

            }

            @Override
            public void getData(List<LoginUser.ResultsBean> list) {
                mList = list;
                mTwoView.onGetData(mList);
            }
        });
    }
}
