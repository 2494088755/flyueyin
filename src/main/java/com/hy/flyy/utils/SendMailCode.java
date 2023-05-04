package com.hy.flyy.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * 生成邮件验证码
 */
@Component
public class SendMailCode {

    @Autowired
    private JavaMailSender javaMailSender;

    //    @Value("${mailCode.from}")
    private String from = "hy17623635027@163.com";

    /**
     * 发送验证码
     *
     * @param to
     * @return
     */
    public String send(String to) {
        //标题
//        String subject = "验证码";
        String subject = "温馨提醒：";
//        String code = generateCode();
        //正文
//        String context = " 您的验证码是：" + code + ",验证码5分钟内有效，您正在登录瑞吉外卖，如非本人操作，请勿透露给其他人。";
        String context = " 亲爱的赵女士，记得多喝水！";
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(from + "(kk)");
            simpleMailMessage.setTo(to);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(context);
            javaMailSender.send(simpleMailMessage);
//            return code;
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //生成验证码
    public String generateCode() {
        return ("" + new Random().nextInt(900000) + 100000).substring(0, 6);
    }

}
