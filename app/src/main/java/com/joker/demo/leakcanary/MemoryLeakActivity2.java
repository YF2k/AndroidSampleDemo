package com.joker.demo.leakcanary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.joker.demo.R;

public class MemoryLeakActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_leak2);
    }
}
