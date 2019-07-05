package com.linhuanjie.common.utils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * created with bananaCard.
 * user: yanghui@700bike.com
 * date: 16/1/14
 * time: 下午9:13
 * description: MD5加密
 */
public class MD5Utils implements Serializable {

    private static final long serialVersionUID = -5865271082310922964L;

    public static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] bytes = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = bytes.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte b : bytes) {
                str[k++] = hexDigits[b >>> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }


    public static String get32MD5(String s, String charset) {
        char[] hexDigits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(s.getBytes(charset));
        } catch (NoSuchAlgorithmException var9) {
            var9.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException var10) {
            var10.printStackTrace();
            return null;
        }

        byte[] tmp = md.digest();
        char[] str = new char[32];
        int k = 0;

        for(int i = 0; i < 16; ++i) {
            byte byte0 = tmp[i];
            str[k++] = hexDigits[byte0 >>> 4 & 15];
            str[k++] = hexDigits[byte0 & 15];
        }

        s = new String(str);
        return s;
    }
}
