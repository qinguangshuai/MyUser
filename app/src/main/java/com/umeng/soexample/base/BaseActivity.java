package com.umeng.soexample.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    private BasePresenter mBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = initLayoutId();
        if(i!=0){
            setContentView(i);
            initView();
            initData();
            initListener();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        mBasePresenter = initPresenter();
        mBasePresenter.attch(initIView());
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mBasePresenter.detach();
        mBasePresenter=null;
    }

    //初始化控件
    public abstract void initView();

    //初始化数据
    public abstract void initData();

    //初始化布局文件
    public abstract int initLayoutId();

    //初始化各种监听
    public abstract void initListener();

    //初始化presenter
    public abstract BasePresenter initPresenter();

    //初始化view接口
    public abstract BaseView initIView();
}
