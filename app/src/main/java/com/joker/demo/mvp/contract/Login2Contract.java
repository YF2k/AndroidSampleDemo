package com.joker.demo.mvp.contract;

import com.joker.demo.mvp.base.IModel;
import com.joker.demo.mvp.base.IPresenter;
import com.joker.demo.mvp.base.IView;

//弊端：实际上baseActivity和basePresenter已经实现了IView和IPresenter接口，所以contract中本不必在继承IView和IPresenter接口，之所以View需要继承IView
public interface Login2Contract {
    interface Model extends IModel {

    }

    interface View extends IView {
        void updateCodeBtnState();//更新获取验证码按钮状态（假设只有获取验证码成功时才改变状态）

    }

    interface Presenter<V extends IView> extends IPresenter<V> {
        void getCode();//获取验证码
    }
}
