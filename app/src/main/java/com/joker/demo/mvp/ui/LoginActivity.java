package com.joker.demo.mvp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.joker.demo.R;
import com.joker.demo.mvp.base.BaseActivity;
import com.joker.demo.mvp.contract.LoginContract;
import com.joker.demo.mvp.presenter.LoginPresenter;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View{

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        presenter.getCode();

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void updateCodeBtnState() {
        //成功之后改变布局
    }
}
