package com.joker.demo.mvp.presenter;

import com.joker.demo.mvp.base.BasePresenter;
import com.joker.demo.mvp.base.IResultCallback;
import com.joker.demo.mvp.contract.Login2Contract;
import com.joker.demo.mvp.contract.LoginContract;
import com.joker.demo.mvp.model.UserCenterModel;
import com.joker.demo.mvp.ui.LoginActivity;

public class Login2Presenter extends BasePresenter<Login2Contract.View> implements Login2Contract.Presenter<Login2Contract.View>{
    UserCenterModel userCenterModel;

    public Login2Presenter(){
        userCenterModel=new UserCenterModel();
    }

    @Override
    public void getCode() {
        userCenterModel.getCode(new IResultCallback() {
            @Override
            public void onSuccess() {
                getView().updateCodeBtnState();
            }

            @Override
            public void onFail() {

            }
        });

    }


    public void getTest(){

    }
}
