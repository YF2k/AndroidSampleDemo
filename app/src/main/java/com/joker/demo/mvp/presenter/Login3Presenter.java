package com.joker.demo.mvp.presenter;

import com.joker.demo.mvp.base.BasePresenter;
import com.joker.demo.mvp.base.IResultCallback;
import com.joker.demo.mvp.contract.Login2Contract;
import com.joker.demo.mvp.contract.Login3Contract;
import com.joker.demo.mvp.model.UserCenterModel;

public class Login3Presenter extends BasePresenter<Login3Contract.View> implements Login3Contract.Presenter{
    UserCenterModel userCenterModel;

    public Login3Presenter(){
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
}
