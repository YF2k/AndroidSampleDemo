package com.joker.demo.mvp.base;

public interface IPresenter<V extends IView> {
    void attachView(V view);
    void onDetach();
    boolean isAttached();
    V getView();
}
