package com.joker.demo.utils;

import android.content.Context;

import com.eclipsesource.v8.V8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JSUtil {
    //加载js文件
    public static String loadJsFile(Context context)
    {
        String result="null";
        InputStream is= null;   //获取用户名与密码加密的js代码
        try {
            is = context.getAssets().open("pdd_get_antiContent.js");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            V8 runtime = V8.createV8Runtime();      //使用J2V8运行js代码并将编码结果返回
            result = runtime.executeStringScript(sb.toString());
            runtime.release();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
