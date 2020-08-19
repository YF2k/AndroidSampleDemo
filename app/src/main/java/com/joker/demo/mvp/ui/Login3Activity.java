package com.joker.demo.mvp.ui;

import android.os.Bundle;

import com.joker.demo.R;
import com.joker.demo.mvp.base.BaseActivity;
import com.joker.demo.mvp.contract.Login3Contract;
import com.joker.demo.mvp.presenter.Login3Presenter;

public class Login3Activity extends BaseActivity<Login3Presenter> implements Login3Contract.View{

    @Override
    protected Login3Presenter createPresenter() {
        return new Login3Presenter();
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
