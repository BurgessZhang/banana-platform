package com.burgess.banana.system.oauth2;


import com.burgess.banana.common.exception.BananaResultException;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * @author free.zhang
 * @project_name banana-platform
 * @package_name com.burgess.banana.system.oauth2
 * @file_name BananaTokenGenerator.java
 * @description token生成
 * @create 2018-04-19 11:09
 */
public class BananaTokenGenerator {

    public static String generateValue() {
        return generateValue(UUID.randomUUID().toString());
    }

    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String toHexString(byte[] data) {
        if(data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length*2);
        for ( byte b : data) {
            r.append(hexCode[(b >> 4) & 0xF]);
            r.append(hexCode[(b & 0xF)]);
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            throw new BananaResultException("生成Token失败", e);
        }
    }

}
