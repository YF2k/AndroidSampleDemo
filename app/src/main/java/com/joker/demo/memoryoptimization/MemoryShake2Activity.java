package com.joker.demo.memoryoptimization;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.joker.demo.R;

public class MemoryShake2Activity extends AppCompatActivity {
    public static Handler mHandler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //创造内存抖动
            for(int i=0;i<=100;i++){
                String arg[] = new String[500000];
            }
            mHandler.sendEmptyMessageDelayed(0,10);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_shake2);
        Button mBtnMemoryShake2 = findViewById(R.id.btn_memory_shake2);
        mBtnMemoryShake2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHandler.sendEmptyMessage(0);
            }
        });
    }
}
