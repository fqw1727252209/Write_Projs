package com.study.platform.util;

import java.security.MessageDigest;

/**
 * MD5加密工具类
 * 
 * @author Student
 * @date 2024-11-25
 */
public class MD5Util {

    /**
     * MD5加密
     * 
     * @param str 待加密字符串
     * @return 加密后的字符串
     */
    public static String encrypt(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] byteDigest = md.digest();
            StringBuilder hexValue = new StringBuilder();
            for (byte b : byteDigest) {
                int val = ((int) b) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5加密失败", e);
        }
    }

}
