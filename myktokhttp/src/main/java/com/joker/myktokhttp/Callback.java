package com.joker.myktokhttp;


public interface Callback {
    void onFailure(Throwable throwable);

    void onResponse(Response response);
}
