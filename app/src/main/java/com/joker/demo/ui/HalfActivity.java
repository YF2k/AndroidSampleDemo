package com.joker.demo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;

public class HalfActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_half);
        WindowManager manager = getWindowManager();
        Display display = manager.getDefaultDisplay();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.height = (int) (display.getHeight()*0.5);
//        params.width = (int) (display.getWidth()*0.5);
        getWindow().setAttributes(params);
    }
}