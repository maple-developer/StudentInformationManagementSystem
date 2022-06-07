package com.maple.service.impl;

import com.maple.service.EmailService;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailServiceImpl implements EmailService {
    @Override
    public boolean sendEmailToVerify(String to, String context) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = contextClassLoader.getResourceAsStream("mail.properties");
        final Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(properties.getProperty("mail.user"), properties.getProperty("mail.password")); //发件人邮件用户名、密码
                }
            });
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom("maple_zh@foxmail.com");
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject("Verification code");
            mimeMessage.setText(context);
            Transport.send(mimeMessage);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
