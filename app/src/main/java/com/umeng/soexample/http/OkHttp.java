package com.umeng.soexample.http;

import com.umeng.soexample.bean.LoginUser;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * date:2018/11/16    18:44
 * author:秦广帅(Lenovo)
 * fileName:OkHttp
 */
public class OkHttp {
    public void jie(String url, final HttpCallback callback){
        OkHttpClient client = new OkHttpClient();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.writeTimeout(3000,TimeUnit.MILLISECONDS);
        builder.readTimeout(3000,TimeUnit.MILLISECONDS);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        call.enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if(code==200){
                    ResponseBody body = response.body();
                    String string = body.string();
                    callback.setData(string);
                }
            }
        });
    }
    public interface HttpCallback{
        void setData(String s);
        void getData(List<LoginUser.ResultsBean> list);
    }
}
