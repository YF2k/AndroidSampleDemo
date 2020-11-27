package com.joker.demo.mvvm.viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.joker.demo.BaseActivity;
import com.joker.demo.R;

public class ViewModelActivity extends BaseActivity {
    private TextView mContentTv;
    private Button mBtnUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_view_model);
        mContentTv = findViewById(R.id.tv_content);
        mBtnUpdate = findViewById(R.id.btn_update);
        //构建ViewModel实例
        UserModel userModel = new  ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(UserModel.class);
        //让TextView观察ViewModel中数据的变化,并实时展示
        userModel.mUserLiveData.observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                mContentTv.setText(user.toString());
            }
        });

        mBtnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //点击按钮  更新User数据  观察TextView变化
                userModel.doSomething();
            }
        });

    }
}