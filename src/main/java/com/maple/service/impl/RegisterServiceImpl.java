package com.maple.service.impl;

import com.maple.entities.User;
import com.maple.mapper.UserMapper;
import com.maple.service.RegisterService;
import com.maple.utils.SqlSessionFactoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class RegisterServiceImpl implements RegisterService {

    /**
     * return the user object if username is exists in database. use to verify username is correct or not with async in login module.
     *
     * @param username username in request
     * @return user object
     */
    @Override
    public boolean verifyUserName(String username) {
        User user = null;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.selectUserByUserName(username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null != user;
    }

    /**
     * Register new user for login management system.
     *
     * @param user return the status for registering. True or False.
     */
    @Override
    public boolean register(User user) {
        boolean flag = false;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User u = mapper.selectUserByUserName(user.getUsername());
            if (null != u) {
                log.info("This username is already exists.");
                return false;
            }
            flag = mapper.register(user);
            sqlSession.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }

    /**
     * Verify the user registered by the given email is exists or not.
     *
     * @param email given by client
     * @return if the user registered by the given email is exist return true else false.
     */
    @Override
    public boolean verifyEmail(String email) {
        boolean flag = false;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            User user = mapper.selectUserByEmail(email);
            if (null != user) {
                log.info("This email have already registered account.");
                flag = true;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return flag;
    }

/*
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
            mimeMessage.setSubject("Verification code for Registration");
            mimeMessage.setText(context);
            Transport.send(mimeMessage);
        } catch (IOException | MessagingException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
*/
}
