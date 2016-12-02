package com.example.administrator.zjlc.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/9/22.
 */
public class MD5Utils {

    //MD5加密 32 位
    public  static String Md5(String str){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0;i<charArray.length;i++){
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexVlue = new StringBuffer();
        for (int i = 0;i<md5Bytes.length;i++){
            int val = ((int)md5Bytes[i])&0xff;
            if (val <16){
                hexVlue.append("0");
            }
            hexVlue.append(Integer.toHexString(val));
        }
        return hexVlue.toString();
    }

    public static String encryptmd5(String str){
        char[] a = str.toCharArray();
        for (int i = 0;i<a.length;i++){
            a[i] = (char) (a[i]^'1');
        }
        String s = new String(a);
        return s;
    }

}
