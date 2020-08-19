package com.joker.demo.mvp.model;

import com.joker.demo.mvp.base.IResultCallback;

public class UserCenterModel {

    public void getCode(IResultCallback callback) {
        //成功
        callback.onSuccess();
        //失败
        callback.onFail();
    }
}
