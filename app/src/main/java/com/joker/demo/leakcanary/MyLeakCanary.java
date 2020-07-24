package com.joker.demo.leakcanary;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;


import java.lang.ref.ReferenceQueue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyLeakCanary {

    private static final String TAG = "MyLeakCanary";
    static Set<String> mRetainedKeys = new CopyOnWriteArraySet<>();
    static ReferenceQueue<Object> queue = new ReferenceQueue<>();

    static Handler mHandler=new Handler();

    public static void install(Application application) {
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {

            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {

            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {

            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                Log.e(TAG, activity.getClass().getName() + " onDestory");
                watch(activity, activity.getClass().getName());
            }
        });
    }

    private static void watch(Object activity, String referenceName) {
        String key = UUID.randomUUID().toString();
        mRetainedKeys.add(key);
        KeyedWeakReference reference =
                new KeyedWeakReference(activity, key, referenceName, queue);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 移除已经被回收的引用
                removeWeaklyReachableReferences();
                // 判断 reference，即 activity 是否内回收了，若被回收了，直接返回
                if(gone(reference)){
                    return;
                }
                // 调用 gc 方法进行垃圾回收
                runGc();
                // 移除已经被回收的引用
                removeWeaklyReachableReferences();
                // activity 还没有被回收，证明发生内存泄露
                if(!gone(reference)){
                    Log.d(TAG,referenceName+"内存泄漏了！！！");
                }
            }
        },5000);

    }

    private static void runGc() {
        Runtime.getRuntime().gc();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.runFinalization();
    }

    private static boolean gone(KeyedWeakReference reference) {
        return !mRetainedKeys.contains(reference.key);
    }

    private static void removeWeaklyReachableReferences() {
        KeyedWeakReference ref;
        while((ref= (KeyedWeakReference) queue.poll())!=null){
            mRetainedKeys.remove(ref.key);
        }
    }




}
