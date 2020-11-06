package com.joker.demo.opensourceframe.leakcanary;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.joker.demo.R;
import com.joker.demo.bitmap.BitmapActivity;


public class MemoryLeakActivity2 extends AppCompatActivity {

    private static final String TAG = "MemoryLeakActivity2";
    public  Bitmap obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak2);
        ImageView imv = findViewById(R.id.iv_big_bmp);
        /**转成bitmap的原因：因为res里面的图片资源在内存中只会保存一份，若是不转成bitmap的话，
         * 即使MemoryLeakActivity2发生内存泄露，但是后续泄露的内存大小其实并不包括图片大小。
        **/
        obj = ((BitmapDrawable)imv.getDrawable()).getBitmap();

          new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("=================");
                }
            }
        }).start();
    }

    //即使发生了内存泄露，MemoryLeakActivity2没能被回收，onDestory()方法也会被调用，生命周期方法
    //是AMS调用的，与gc回收机制无关
    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestory1+++++++");
        super.onDestroy();
        Log.d(TAG,"onDestory2+++++++");

    }
}
