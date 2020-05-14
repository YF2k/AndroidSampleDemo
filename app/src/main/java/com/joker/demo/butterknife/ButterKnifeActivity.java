package com.joker.demo.butterknife;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import android.os.Bundle;
import android.widget.TextView;

import com.joker.demo.R;

public class ButterKnifeActivity extends AppCompatActivity {
    private Unbinder unbinder;

    @BindView(R.id.tv_title)
    TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butter_knife);
        unbinder= ButterKnife.bind(this);



    }


    @OnClick(R.id.btn_submit)
    public void submit(){
        mTvTitle.setText("hello world");
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
