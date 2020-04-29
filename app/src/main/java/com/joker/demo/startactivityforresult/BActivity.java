package com.joker.demo.startactivityforresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;

public class BActivity extends BaseActivity {
    private static final String TAG = "BActivity";
    Button mBtnOpenA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        mBtnOpenA=findViewById(R.id.btn_openA);

        mBtnOpenA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTargetActivityForResult(AActivity.class,1002);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"B onResume...");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"B onPause...");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"B调用了onActivityResult...");
    }
}
