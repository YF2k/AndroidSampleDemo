package com.joker.demo.mvp.contract;

import com.joker.demo.mvp.base.IModel;
import com.joker.demo.mvp.base.IPresenter;
import com.joker.demo.mvp.base.IView;


//弊端：view层会直接拿到LoginActivity对象，可以访问生命周期等方法，暴露得太多
public interface LoginContract {
    interface Model extends IModel {

    }

    interface View {
        void updateCodeBtnState();//更新获取验证码按钮状态（假设只有获取验证码成功时才改变状态）

    }

    interface Presenter {
        void getCode();//获取验证码
    }
}
