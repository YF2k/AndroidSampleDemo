package com.joker.demo.glide;

public class Preconditions {
    public static<T> T checkNotNull(T arg){
        return checkNotNull(arg,"Argument must not be null");
    }

    public static<T> T checkNotNull(T arg,String message){
        if(arg==null){
            throw new NullPointerException(message);
        }
        return arg;
    }
}
