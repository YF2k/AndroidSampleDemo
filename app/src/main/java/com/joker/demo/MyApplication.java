package com.joker.demo;

import android.app.Application;

import com.joker.demo.crash.CrashHandler;
import com.joker.demo.leakcanary.MyLeakCanary;
import com.squareup.leakcanary.LeakCanary;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        }
        LeakCanary.install(this);
        MyLeakCanary.install(this);
        CrashHandler.getInstance().init(this);
    }
}
