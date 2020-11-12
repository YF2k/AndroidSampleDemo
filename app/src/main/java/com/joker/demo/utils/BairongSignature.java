package com.joker.demo.utils;
import android.text.TextUtils;
import android.util.Base64;

import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.crypto.Cipher;

/**
 * 文件名 BairongSignature
 * 创建者 weilu
 * 创建时间 2020-03-11 12:55
 */
public class BairongSignature {
    private static final String charset = "utf-8";
    private static final String signTypeSHA1 = "SHA1withRSA";
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final String SHA1 = "SHA1";

    public static String signSHA1(Map<String, String> signParams, String privateKey) {
        return initSign(signTypeSHA1, signParams, privateKey);
    }

    /**
     * 根据类型生成签名
     *
     * @param privateKey
     */
    private static String initSign(String signType, Map<String, String> signParams, String privateKey) {
        String result = null;
        try {
            String content = getSignContent(signParams);
            PrivateKey priKey = getPrivateKeyFromPKCS8(privateKey);
            Signature signature = Signature.getInstance(signType);
            signature.initSign(priKey);
            signature.update(content.getBytes(charset));
            byte[] signed = signature.sign();
            result = Base64.encodeToString(signed, Base64.NO_WRAP);
        } catch (Exception e) {

            e.printStackTrace();
        }
        if (result == null) {
            result = "";
        }
        return result;
    }

    /**
     * 参数格式转换Map转String
     *
     * @param sortedParams
     * @return
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    }

    /**
     * 私钥对象生成
     *
     * @param priKey
     * @return
     */
    private static PrivateKey getPrivateKeyFromPKCS8(String priKey) {
        PKCS8EncodedKeySpec priPKCS8;
        PrivateKey privateKey = null;
        try {
            priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(priKey, Base64.NO_WRAP));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            privateKey = keyf.generatePrivate(priPKCS8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateKey;
    }

    /**
     * RSA-公钥加密过程
     *
     * @param input
     * @param pubKey
     * @return
     */
    public static String encryptRSA(String input, String pubKey) {
        if (!StringUtil.checkStr(input)) {
            return "";
        }
        String result = null;
        PublicKey publicKey = getPublicKeyFromX509(pubKey);
        try {
            // 使用默认RSA
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(input.getBytes());
            result = Base64.encodeToString(output, Base64.NO_WRAP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == null) {
            result = "";
        }
        return result;
    }

    /**
     * RSA-私钥解密过程
     *
     * @param input
     * @param priKey
     * @return
     */
    public static String decryptRSA(String input, String priKey) {
        String result = null;
        PrivateKey privateKey = getPrivateKeyFromPKCS8(priKey);

        try {
            // 使用默认RSA
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] output = cipher.doFinal(Base64.decode(input, Base64.NO_WRAP));
            result = new String(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (result == null) {
            result = "";
        }
        return result;
    }

    /**
     * 公钥对象生成
     *
     * @param pubKey
     * @return
     */
    private static PublicKey getPublicKeyFromX509(String pubKey) {
        PublicKey publicKey = null;
        try {
            X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decode(pubKey, Base64.NO_WRAP));
            java.security.KeyFactory keyFactory;
            keyFactory = java.security.KeyFactory.getInstance("RSA");
            // 取公钥匙对象
            publicKey = keyFactory.generatePublic(bobPubKeySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicKey;
    }


    private static String getFormattedText(byte[] bytes) {
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        // 把密文转换成十六进制的字符串形式
        for (int j = 0; j < len; j++) {
            buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
            buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
        }
        return buf.toString();
    }

    public static String encryptSha1(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA1);
            messageDigest.update(str.getBytes());
            return getFormattedText(messageDigest.digest());
        } catch (Exception e) {
            return str;
        }
    }


}
