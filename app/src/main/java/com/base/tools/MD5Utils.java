package com.base.tools;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by KXF on 2018/5/25.
 */

public class MD5Utils {


    private static String MD5 = "MD5";
    private static String ENCODE = "UTF-8";

    public static void main(String[] args) {
        String content = "847372737bdh";
        String key = "kjssuw/-";
        String hex = toHex(md5(content + key));
        System.err.println(hex);
    }

    public static byte[] md5(String s) {
        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance(MD5);
            algorithm.reset();
            algorithm.update(s.getBytes(ENCODE));
            byte[] messageDigest = algorithm.digest();
            return messageDigest;
        } catch (Exception e) {
        }
        return null;
    }

    public static String params(Map<String, Object> param) {
        Set<String> keySet = param.keySet();
        Iterator<String> iterator = keySet.iterator();
        StringBuilder stringBuilder = new StringBuilder(100);
        while (iterator.hasNext()) {
            String key = iterator.next();
            stringBuilder.append(key + "=" + param.get(key) + "&");
        }
        String result = stringBuilder.substring(0, stringBuilder.length() - 1);
        return result;
    }

    public static final String toHex(byte hash[]) {
        if (hash == null) {
            return null;
        }
        StringBuffer buf = new StringBuffer(hash.length * 2);
        int i;

        for (i = 0; i < hash.length; i++) {
            if ((hash[i] & 0xff) < 0x10) {
                buf.append("0");
            }
            buf.append(Long.toString(hash[i] & 0xff, 16));
        }
        return buf.toString();
    }

    public static String hash(String s) {
        try {
            return new String(toHex(md5(s)).getBytes(ENCODE), ENCODE);
        } catch (Exception e) {
            return s;
        }
    }

    /**
     * 对密码按照用户名，密码，盐进行加密
     *
     * @param username 用户名
     * @param password 密码
     * @param salt     盐
     * @return
     */
    public static String encryptPassword(String username, String password, String salt) {
        return hash(username + password + salt);
    }

    /**
     * 对密码按照密码，盐进行加密
     *
     * @param password 密码
     * @param salt     盐
     * @return
     */
    public static String encryptPassword(String password, String salt) {
        return hash(password + salt);
    }
}
