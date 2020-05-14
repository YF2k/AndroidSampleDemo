package com.joker.demo.butterknife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.joker.annotation.XyBindView;
import com.joker.demo.R;

public class XyKnifeActivity extends AppCompatActivity {
    @XyBindView(R.id.tv_xyknife)
    TextView mTvXyKnife;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xy_knife);
        XyKnife.bind(this);

        mTvXyKnife.setText("hello World");
    }
}
