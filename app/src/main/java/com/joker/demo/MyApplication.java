package com.joker.demo;

import android.app.Application;

import com.joker.demo.crash.CrashHandler;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CrashHandler.getInstance().init(this);
    }
}
