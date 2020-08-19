package com.joker.demo.mvp.base;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class BasePresenter<V extends IView> implements IPresenter<V> {
    private WeakReference<V> wf;
    private V proxyView;

    @Override
    public void attachView(V view) {
        wf = new WeakReference<>(view);
        // 用代理对象 动态代理
        Object proxyObj = Proxy.newProxyInstance(view.getClass().getClassLoader(), view.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                if (isAttached()) {
                    return method.invoke(wf.get(), args);
                }
                return null;
            }
        });
        proxyView = (V) proxyObj;
    }

    @Override
    public void onDetach() {
        if (this.wf != null) {
            this.wf.clear();
        }
        this.wf = null;
    }

    @Override
    public boolean isAttached() {
        return this.wf != null && this.wf.get() != null;
    }

    @Override
    public V getView() {
        return proxyView;
    }
}
