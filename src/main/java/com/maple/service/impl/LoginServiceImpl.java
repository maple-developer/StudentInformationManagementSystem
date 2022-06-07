package com.maple.service.impl;

import com.maple.entities.User;
import com.maple.mapper.UserMapper;
import com.maple.service.LoginService;
import com.maple.utils.SqlSessionFactoryUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

@Slf4j
public class LoginServiceImpl implements LoginService {

    /**
     * verify the login with username and password.
     *
     * @param username username for login
     * @param passwd   password for the username for login
     * @return the result of verify login, true or false.
     */
    @Override
    public User  loginByUserNameAndPassword(String username, String passwd) {
        User user = null;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.selectUserByUserNameAndPassword(username, passwd);
            if (null == user) {
                log.info("Password is error.");
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User loginByEmailAndPassword(String email, String passwd) {
        User user = null;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.selectUserByEmailAndPassword(email, passwd);
            log.info("{}", user);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User verifyUserByUserName(String username) {
        User user = null;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.selectUserByUserName(username);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public User verifyUserByEmail(String email) {
        User user = null;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.selectUserByEmail(email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}
