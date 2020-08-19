package com.joker.demo.mvp.presenter;

import com.joker.demo.BaseActivity;
import com.joker.demo.mvp.base.BasePresenter;
import com.joker.demo.mvp.base.IResultCallback;
import com.joker.demo.mvp.contract.LoginContract;
import com.joker.demo.mvp.model.UserCenterModel;
import com.joker.demo.mvp.ui.LoginActivity;

public class LoginPresenter extends BasePresenter<LoginActivity> implements LoginContract.Presenter{
    UserCenterModel userCenterModel;

    public LoginPresenter(){
        userCenterModel=new UserCenterModel();
    }

    @Override
    public void getCode() {
        userCenterModel.getCode(new IResultCallback() {
            @Override
            public void onSuccess() {
                getView().updateCodeBtnState();
                getView().showLoading();
            }

            @Override
            public void onFail() {

            }
        });

    }
}
