package com.joker.demo.startactivityforresult;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;

import androidx.annotation.Nullable;


//adb shell dumpsys activity---该命令可以查看activity栈状态
//singleTask----栈中只有一个实例
public class AActivity extends BaseActivity {
    private static final String TAG = "AActivity";
    Button mBtnOpenB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        mBtnOpenB=findViewById(R.id.btn_openB);

        mBtnOpenB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTargetActivityForResult(BActivity.class,1001);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"A onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"A onPause...");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"A调用了onActivityResult...");
    }
}
