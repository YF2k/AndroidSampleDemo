package com.joker.demo.mvvm.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

//最后当我们数据源改变的时候，我们需要调用 livedata 的 setValue 或者 postvalue 方法。他们之间的区别是， 调用 setValue 方法，Observer 的 onChanged 方法会在调用 serValue 方法的线程回调。而
//postvalue 方法，Observer 的 onChanged 方法将会在主线程回调。
public class UserModel extends ViewModel {

    public final MutableLiveData<User> mUserLiveData = new MutableLiveData<>();

    public UserModel(){
        //模拟从网络加载用户信息
        mUserLiveData.postValue(new User(1,"name1"));
    }

    //模拟 进行一些数据骚操作
    public void doSomething() {
        User user = mUserLiveData.getValue();
        if (user != null) {
            user.age = 15;
            user.name = "name15";
            mUserLiveData.setValue(user);
        }
    }
}
