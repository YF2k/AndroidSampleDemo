package com.joker.demo.jetpack.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

public class LocationUtil implements LifecycleObserver {

    private static final String TAG = "LocationUtil";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void createLocation(){
        Log.d(TAG,"lifecycle:onCreate");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void startLocation(){
        Log.d(TAG,"lifecycle:onStart");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resumeLocation(){
        Log.d(TAG,"lifecycle:onResume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pauseLocation(){
        Log.d(TAG,"lifecycle:onPause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stopLocation(){
        Log.d(TAG,"lifecycle:onStop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destoryLocation(){
        Log.d(TAG,"lifecycle:onDestory");
    }

}
