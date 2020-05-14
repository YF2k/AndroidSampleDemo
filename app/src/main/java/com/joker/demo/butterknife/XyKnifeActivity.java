package com.joker.demo.butterknife;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joker.annotation.XyBindView;
import com.joker.demo.BaseActivity;
import com.joker.demo.R;

public class XyKnifeActivity extends BaseActivity {
    @XyBindView(R.id.tv_xyknife)
    TextView mTvXyKnife;

    @XyBindView(R.id.btn_butterknife)
    Button mBtnButterKnife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xy_knife);
        XyKnife.bind(this);

        mTvXyKnife.setText("hello World");
        mBtnButterKnife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTargetActivity(ButterKnifeActivity.class);
            }
        });
    }
}
