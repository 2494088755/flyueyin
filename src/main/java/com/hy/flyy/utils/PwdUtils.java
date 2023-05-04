package com.hy.flyy.utils;

import cn.hutool.crypto.SecureUtil;

import java.util.UUID;

/**
 * @author 黄勇
 * @since 2023/4/26
 */
public class PwdUtils {

    /**
     * 生成随即盐
     * @return
     */
    public static String generateSalt() {
        return UUID.randomUUID().toString();
    }

    /**
     * 加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static String encryption(String password, String salt) {
        return SecureUtil.sha256(password+salt);
    }

}
