package com.joker.demo.utils;

public class StringUtil {

    /**
     * 字符串非空 return true
     * @param str
     * @return
     */
    public static boolean checkStr(CharSequence str) {
        if (null == str) {
            return false;
        }
        if ("".equals(str)) {
            return false;
        }
        if ("".equals(str.toString().trim())) {
            return false;
        }
        if ("null".equals(str)) {
            return false;
        }
        return true;
    }


}
