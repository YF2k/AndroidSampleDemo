package com.joker.demo.utils;

import org.json.JSONObject;

import java.util.Iterator;

public class EncryptUtil {

    public static void main(String[] args) {
        String input = "{name:hec}";
        String ans = rsaEncrypt(input);
        rsaDecrypt(ans);
    }

    //生成数据完整性sign
    public static final String RSA_PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJaBnB2fqV4lTKbvw0Sd2536EMSA2F2uv2sIxHIQcjwgkhHCjZDzDUn9MZyHRxNH2RtjMCCvbPQh7CSu+7SRi07UT7Fpl0Jh461cBThIxfK8U1nnxEgKlNdwYGxVHhHM8GAZNM0AmVk8VBhUtEj9OELkVVH97tRVfmjHCwRIj/9ZAgMBAAECgYAcQQKcsQ9rhBcKs7H1nKjQ1FP3f7SeiaKXplKykxHO5dJmER7gWjBhdm2s09xs6yz/rjQnvqb2gbPCAzNvZ28+N+O1evQ0PY0hannanoz+D73SUTfesUbG1uSLo5GPZAT/mW/eWhKBgoo5yoyMD7pWLCAdnye6y4bRacqIZo63MQJBAN7l2hgSLSR3nqtibqx5KwZMJL7XzwC1rdSZTd6bL+xoHP02zromD6H6TukFwdJ6FuWNeQDsr+hNLa0x4oqZQc0CQQCs25HOX7/evQA8lZ04A4YBUE4A8qJBi0M1illI2sFM9E4LlfU+67uGgWXxTaIw1NAeXHE7/JZtiIV6BggFhRe9AkEAohfO+VBGic1/kqy7RSu8cRDwa+Ruwdpc9k0iBq8eM7Im2rF/tnk1RxrLRcQNBm4ItpiiFV0KM0nk3J15XEdFRQJBAJ5iqh0hGtvq7haVOHOkttrpTDAOIqJQCos6c0kQOGJc0E5JX2gB89fxJQmPiveXaAMJzS+b5/IBT9xZPmxgYTkCQFkpbN8fjF75yjZM3E9TUG2QOEg20V8NFJ9I3H0v7SO2GjS+fPZ3QuOZQAuqSOeNz+aPaFF86EjJpYAyC1Z//zw=";
    //rsa加密公钥
    public static final String RSA_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCRssROo9nwQ2fuoOAQqCwBouAngmwsU4D10LWsIuPcfeMD7P70clnNF2aJRr89gnLwDOhp+2OVACRPeynW5Bb6rizBNPp/wxpUKKN2awOZKb7V2Boz36yg2rmU3rCGSuC7oZ/N0pdqunmWE3/+byzpS/3NFdApfAh22QlCdH7a+QIDAQAB";

    //rsa加密
    public static String rsaEncrypt(String jsonStr) {
        JSONObject dataObj = new JSONObject();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            int len = jsonObject.length();
            if (len > 0) {
                Iterator<String> it = jsonObject.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = jsonObject.optString(key);
                    String rsaValue = BairongSignature.encryptRSA(value, RSA_PUBLIC_KEY);
                    dataObj.put(key, rsaValue);
                }
            }
        } catch (Exception e) {

        }
        System.out.println("加密串：" + dataObj.toString());
        return dataObj.toString();

    }

    //rsa解密
    public static void rsaDecrypt(String jsonStr) {
        JSONObject dataObj = new JSONObject();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            int len = jsonObject.length();
            if (len > 0) {
                Iterator<String> it = jsonObject.keys();
                while (it.hasNext()) {
                    String key = it.next();
                    String value = jsonObject.optString(key);
                    String rsaValue = BairongSignature.decryptRSA(value, RSA_PRIVATE_KEY);
                    dataObj.put(key, rsaValue);
                }
            }
        } catch (Exception e) {

        }
        System.out.println("解密串：" + dataObj.toString());

    }
}
