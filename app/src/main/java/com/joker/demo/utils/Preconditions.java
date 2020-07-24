package com.joker.demo.utils;

public class Preconditions {

    /**
     * @return
     */
    public static <T>T checkNotNull(T instance,String name) {
        if (null == instance) {
            throw new NullPointerException(name + " must not be null");
        }
        return instance;
    }

}
