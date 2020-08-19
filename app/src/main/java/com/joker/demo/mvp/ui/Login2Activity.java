package com.joker.demo.mvp.ui;

import android.os.Bundle;

import com.joker.demo.R;
import com.joker.demo.mvp.base.BaseActivity;
import com.joker.demo.mvp.contract.Login2Contract;
import com.joker.demo.mvp.contract.LoginContract;
import com.joker.demo.mvp.presenter.Login2Presenter;
import com.joker.demo.mvp.presenter.LoginPresenter;

public class Login2Activity extends BaseActivity<Login2Contract.Presenter> implements Login2Contract.View{

    @Override
    protected Login2Contract.Presenter createPresenter() {
        return new Login2Presenter();
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
