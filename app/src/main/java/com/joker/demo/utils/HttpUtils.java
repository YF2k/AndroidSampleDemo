package com.joker.demo.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HttpUtils {
    OkHttpClient client;
    private static volatile HttpUtils httpUtils = null;

    private HttpUtils(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(10L, TimeUnit.SECONDS);
        builder.readTimeout(25L,TimeUnit.SECONDS);
        builder.writeTimeout(10000L, TimeUnit.MILLISECONDS);
        client=builder.build();
    }

    public static HttpUtils getInstance(){
        if(httpUtils==null){
            synchronized (HttpUtils.class) {
                if (httpUtils == null) {
                    httpUtils = new HttpUtils();
                }
            }
        }
        return httpUtils;
    }


    public void getHttp(){

    }
}
