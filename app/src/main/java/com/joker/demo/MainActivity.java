package com.joker.demo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.joker.demo.viewpager2.Vp2FragmentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button mBtnA,mBtnVp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        initView();

        mBtnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.setClass(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        Log.e("xiang","A create");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_vp2:
                startActivity(new Intent(MainActivity.this, Vp2FragmentActivity.class));
                break;
        }
    }

    private void initView() {
        mBtnA=findViewById(R.id.btn_A);
        mBtnVp2=findViewById(R.id.btn_vp2);
    }

    private void initActionBar() {
        ActionBar actionBar= getSupportActionBar();
        actionBar.setTitle("目录页");
        actionBar.setSubtitle("moveTaskToBack");

    }


}
