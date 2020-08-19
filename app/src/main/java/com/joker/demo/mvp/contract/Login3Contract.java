package com.joker.demo.mvp.contract;

import com.joker.demo.mvp.base.IModel;
import com.joker.demo.mvp.base.IView;

public interface Login3Contract {
    interface Model extends IModel {

    }

    interface View extends IView {
        void updateCodeBtnState();//更新获取验证码按钮状态（假设只有获取验证码成功时才改变状态）

    }

    interface Presenter {
        void getCode();//获取验证码
    }
}
