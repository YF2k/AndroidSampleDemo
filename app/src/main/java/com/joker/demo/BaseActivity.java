package com.joker.demo;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {
    public Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        unbinder= ButterKnife.bind(this);
    }

    protected void initView() {

    }

    public void startTargetActivity(Class target){
        startActivity(new Intent(this,target));
    }

    public void startTargetActivityForResult(Class target,int requestCode){
        startActivityForResult(new Intent(this,target),requestCode);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

}
