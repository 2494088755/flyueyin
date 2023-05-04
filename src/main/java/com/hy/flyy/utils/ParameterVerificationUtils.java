package com.hy.flyy.utils;

/**
 * @author 黄勇
 * @since 2023/4/26
 */
public class ParameterVerificationUtils {

    /**
     * 用户名长度5-20位，由数字，字母，下划线组成，且不能以数字开头，字母不区分大小写。
     *
     * @return 成功返回true，否则返回false
     */
    public static boolean checkUsername(String username) {
        String regExp = "^[A-Za-z_][A-Za-z\\d_]{4,19}$";
        return username.matches(regExp);
    }

    /**
     * 密码长度为6-20位，由数字，字母，下划线组成，且至少包含两种及以上字符，字母区分大小写。
     *
     * @param password 需要校验的密码
     * @return 成功返回true，否则返回false
     */
    public static boolean checkPassword(String password) {
        String regExp = "^(?![0-9]+$)(?![a-z]+$)(?![A-Z]+$)(?![_]+$)[0-9_A-Za-z]{5,19}$";
        return password.matches(regExp);
    }

    public static boolean checkEmail(String email) {
        String regExp = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        return email.matches(regExp);
    }

    /**
     * 如果三个条件都不符合则返回true
     *
     * @param username
     * @param password
     * @param email
     * @return
     */
    public static boolean checkAll(String username, String password, String email) {
        return !checkUsername(username) || !checkPassword(password) || !checkEmail(email);
    }

    public static boolean checkPhone(String phone) {
        String regExp = "(13\\d|14[579]|15[^4\\D]|17[^49\\D]|18\\d)\\d{8}";
        return phone.matches(regExp);
    }
}
