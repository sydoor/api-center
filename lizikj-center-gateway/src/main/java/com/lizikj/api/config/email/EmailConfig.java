package com.lizikj.api.config.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 *  暂时取消
 * Created by liangxiaolin on 2017/9/26.
 */
//@Configuration
public class EmailConfig {
//    @Value("email.smtp_host")
//    private String SMTP_HOST ;
//    @Value("email.from_user_name")
//    private String FROM_USER_NAME;
//    @Value("email.from_user_password")
//    private String FROM_USER_PASSWORD;
//    @Value("email.smtp_port")
//    private String SMTP_PORT;
//
//    @Bean
//    public MailSender mailSender(){
//        JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
//        mailSenderImpl.setDefaultEncoding("UTF-8");
//        mailSenderImpl.setHost(SMTP_HOST);
//        mailSenderImpl.setPort(Integer.valueOf(SMTP_PORT));
//        mailSenderImpl.setUsername(FROM_USER_NAME);
//        mailSenderImpl.setPassword(FROM_USER_PASSWORD);
//
//        return mailSenderImpl;
//    }
}
