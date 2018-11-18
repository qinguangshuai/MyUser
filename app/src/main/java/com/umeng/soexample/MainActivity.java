package com.umeng.soexample;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.soexample.base.BaseActivity;
import com.umeng.soexample.base.BasePresenter;
import com.umeng.soexample.base.BaseView;
import com.umeng.soexample.presenter.LoginPresenter;
import com.umeng.soexample.view.LoginView;

import java.util.Iterator;
import java.util.Map;

public class MainActivity extends BaseActivity implements LoginView {

    private String TAG = this.getClass().getSimpleName();
    private Button btn1, btn2, btn3;
    private LoginPresenter mLoginPresenter;
    private EditText edit1;
    private EditText edit2;
    private ImageView image;

    @Override
    public void initView() {
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        image = findViewById(R.id.image1);
    }

    @Override
    public void initData() {

    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edit1.getText().toString().trim();
                String sex = edit2.getText().toString().trim();
                if(name.equals("")&&sex.equals("")){
                    Toast.makeText(MainActivity.this,"账户密码为空",Toast.LENGTH_SHORT).show();
                    return;
                }else if(name.equals("111")&&sex.equals("111")){
                    mLoginPresenter.login("111","111");
                    Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                    startActivity(intent);
                }else if(sex.equals("")){
                    Toast.makeText(MainActivity.this,"账户密码为空",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public BasePresenter initPresenter() {
        mLoginPresenter = new LoginPresenter(MainActivity.this,this);
        return mLoginPresenter;
    }

    @Override
    public BaseView initIView() {
        return this;
    }
/*
    分享
    public void qqShare(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
        UMImage umImage = new UMImage(MainActivity.this,R.mipmap.ic_launcher);
        UMWeb web = new UMWeb("https://www.baidu.com/");
        web.setTitle("This is music title");
        web.setThumb(umImage);
        web.setDescription("my description");
        new ShareAction(MainActivity.this).withText("hello").withMedia(web)
                .withMedia(web)
                .setDisplayList(SHARE_MEDIA.SINA)
                .open();
    }
*/

    public void qqLogin(View view) {
        //每次登录授权
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        UMShareAPI.get(MainActivity.this).setShareConfig(config);
        authorization(SHARE_MEDIA.QQ);

    }

    public void weixinLogin(View view) {
        authorization(SHARE_MEDIA.WEIXIN);
    }

    //授权
    private void authorization(SHARE_MEDIA share_media) {
        UMShareAPI.get(this).getPlatformInfo(this, share_media, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.d(TAG, "onComplete " + "授权完成");
                String uid = map.get("uid");
                String openid = map.get("openid");
                String unionid = map.get("unionid");
                String access_token = map.get("access_token");
                String refresh_token = map.get("refresh_token");
                String expires_in = map.get("expires_in");
                //称呼
                String name = map.get("name");
                //性别
                String gender = map.get("gender");
                //头像
                String iconurl = map.get("iconurl");
                Toast.makeText(getApplicationContext(), "openid=" + openid + ",name=" + name+"///"+iconurl, Toast.LENGTH_SHORT).show();
                Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
                while (iterator.hasNext()){
                    Map.Entry<String,String> next = iterator.next();
                    if(next.getKey().equals("iconurl")){
                        Picasso.with(MainActivity.this).load(next.getValue()).into(image);
                        Intent intent = new Intent(MainActivity.this, TwoActivity.class);
                        intent.putExtra("iconurl",iconurl);
                        intent.putExtra("name",name);
                        startActivity(intent);
                        finish();
                    }
                    if(next.getKey().equals("name")){
                        edit1.setText(next.getValue());
                    }
                    if(next.getKey().equals("openid")){
                        edit2.setText(next.getValue());
                    }
                }
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.d(TAG, "onError " + "授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.d(TAG, "onCancel " + "授权取消");
            }
        });
    }

    //分享
    private UMShareListener shareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "成功了", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable throwable) {
            Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(MainActivity.this, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

    @Override
    public void onSuccess(String result) {
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailer(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onKong() {
        Toast.makeText(this,"空",Toast.LENGTH_SHORT).show();
    }
}
