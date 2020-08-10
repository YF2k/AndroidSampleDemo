package com.joker.myktokhttp.chain;

import com.joker.myktokhttp.Response;

import java.io.IOException;

public interface Interceptor {
    Response intercept(InterceptorChain interceptorChain) throws IOException;
}
