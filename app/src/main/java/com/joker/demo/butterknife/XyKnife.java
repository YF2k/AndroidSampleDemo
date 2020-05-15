package com.joker.demo.butterknife;

import android.app.Activity;
import android.util.Log;

public class XyKnife {
    public static final String TAG="XyKnife";
    public static void bind(Activity activity){
        // 获取到对应的 Activity的class
        Class<? extends Activity> aClass = activity.getClass();
        //获取到全路径字符串----com.joker.demo.butterknife.XyKnifeActivity
        String canonicalName = aClass.getCanonicalName();
        try {

            // 因为我们的规则是在XXX_ViewBinding 所以我们找到对应的MainActivity_ViewBinding的class
            Class<?> bindingClass = aClass.getClassLoader().loadClass(canonicalName + "_ViewBinding");
            // 获取到他的构造方法，传入对应的参数类型，执行它。
            bindingClass.getConstructor(aClass).newInstance(activity);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d(TAG,canonicalName);
    }
}
