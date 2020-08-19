package com.joker.demo.sparsearray;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joker.demo.R;

public class SparseArrayActivity extends AppCompatActivity {
    Button mBtnSave, mBtnGet;
    TextView mTvValue;
    SparseArray<String> array=new SparseArray<>();
    int key=0;
    String value="baojie";
    private static final String TAG="SparseArrayActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sparse_array);

        mBtnSave = findViewById(R.id.btn_save);
        mBtnGet = findViewById(R.id.btn_get);
        mTvValue = findViewById(R.id.tv_value);

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array.put(key,value);
                key++;
                value=value+key;
            }
        });

        mBtnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //经测试，indexofkey方法当key不存在时会返回负数，不一定是-1，测试中也有-2的情况
                mTvValue.setText("indexofkey:"+array.indexOfKey(key)+":::"+"value:"+array.get(key));
                key--;
            }
        });


    }
}
