package com.joker.demo.leakcanary;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ActivityRefWatcher {

    private static final String TAG = "ActivityRefWatcher";
    private Application.ActivityLifecycleCallbacks lifecycleCallbacks=new Application.ActivityLifecycleCallbacks() {
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
            ActivityRefWatcher.this.onActivityDestroyed(activity);
        }
    };


    void onActivityDestroyed(Activity activity) {
        Log.d(TAG,"");
//        refWatcher.watch(activity);
    }
}
