package com.joker.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startTargetActivity(Class target){
        startActivity(new Intent(this,target));
    }

    public void startTargetActivityForResult(Class target,int requestCode){
        startActivityForResult(new Intent(this,target),requestCode);
    }

}
