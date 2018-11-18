package com.umeng.soexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.umeng.soexample.adapter.TwoAdapter;
import com.umeng.soexample.bean.LoginUser;
import com.umeng.soexample.presenter.TwoPresenter;
import com.umeng.soexample.util.UrlUtil;
import com.umeng.soexample.view.TwoView;

import java.util.ArrayList;
import java.util.List;

public class TwoActivity extends AppCompatActivity implements TwoView {

    private List<LoginUser.ResultsBean> list = new ArrayList<>();
    private TwoPresenter mTwoPresenter;
    private RecyclerView recycle;
    private ImageView mImageView;
    private Button btn4,btn5;
    private TextView text0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        recycle = findViewById(R.id.recycle);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        text0 = findViewById(R.id.text0);
        Intent intent = getIntent();
        String iconurl = intent.getStringExtra("iconurl");
        String name = intent.getStringExtra("name");
        text0.setText(name);
        mImageView = findViewById(R.id.image);
        mImageView.setVisibility(View.VISIBLE);
        Picasso.with(TwoActivity.this).load(iconurl).into(mImageView);
        mTwoPresenter = new TwoPresenter(this);
        mTwoPresenter.two(UrlUtil.Gong);
        layout(false,true);
    }

    @Override
    public void onSuccess(String result) {
        Toast.makeText(TwoActivity.this, "成功了", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailer(String msg) {
        Toast.makeText(TwoActivity.this, "失败了", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGetData(final List<LoginUser.ResultsBean> list) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final TwoAdapter adapter = new TwoAdapter(getApplicationContext(),list);
                adapter.setClick(new TwoAdapter.RecycleitemClick() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        Toast.makeText(TwoActivity.this,position+"",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(TwoActivity.this,ThreeActivity.class);
                        intent.putExtra("image",list.get(position).getUrl());
                        startActivity(intent);
                    }
                });
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        adapter.addData(0);
                    }
                });
                btn5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(list.size()>1){
                            adapter.removeData(0);
                        }else{
                            Toast.makeText(getApplication(),"仅剩一条数据",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                recycle.setAdapter(adapter);
            }
        });
    }
    public void layout(boolean a,boolean b){
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,b?StaggeredGridLayoutManager.VERTICAL:StaggeredGridLayoutManager.HORIZONTAL);
        recycle.setLayoutManager(staggeredGridLayoutManager);
    }
}
