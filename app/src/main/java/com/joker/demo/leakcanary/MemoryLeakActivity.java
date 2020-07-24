package com.joker.demo.leakcanary;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;

public class MemoryLeakActivity extends BaseActivity {

    private static final String TAG = "MemoryLeakActivity";
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak);

        next = (Button) findViewById(R.id.btn_start_memory2);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTargetActivity(MemoryLeakActivity2.class);
                finish();
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("=================");
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG,"onDestory1===");
        super.onDestroy();
        Log.d(TAG,"onDestory2===");
    }
}
