package com.umeng.soexample.view;

import com.umeng.soexample.base.BaseView;
import com.umeng.soexample.bean.LoginUser;

import java.util.List;

/**
 * date:2018/11/16    18:49
 * author:秦广帅(Lenovo)
 * fileName:TwoView
 */
public interface TwoView extends BaseView {
    void onGetData(List<LoginUser.ResultsBean> list);
}
