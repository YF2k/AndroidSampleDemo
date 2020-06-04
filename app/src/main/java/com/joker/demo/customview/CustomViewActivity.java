package com.joker.demo.customview;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;

public class CustomViewActivity extends BaseActivity implements View.OnClickListener{
    Button mBtnPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();


    }

    protected void initView() {
        setContentView(R.layout.activity_custom_view);
        mBtnPath=findViewById(R.id.btn_path);
        mBtnPath.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.btn_path:
                startTargetActivity(PathActivity.class);
                break;
        }
    }
}
