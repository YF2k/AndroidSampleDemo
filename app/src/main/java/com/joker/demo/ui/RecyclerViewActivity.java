package com.joker.demo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;

import java.util.ArrayList;

public class RecyclerViewActivity extends BaseActivity {
    private ArrayList<String> mDatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        //创造数据
        for (int i = 1; i <= 100; i++) {
            mDatas.add("第 " + i + " 个item");
        }

        RecyclerView mRv = findViewById(R.id.rv_demo);

        //线性布局
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRv.setLayoutManager(linearLayoutManager);

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mDatas);
        mRv.setAdapter(adapter);
    }
}