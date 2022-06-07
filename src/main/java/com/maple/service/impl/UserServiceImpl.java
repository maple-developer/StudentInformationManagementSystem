package com.maple.service.impl;

import com.maple.entities.User;
import com.maple.mapper.UserMapper;
import com.maple.service.UserService;
import com.maple.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;

public class UserServiceImpl implements UserService {

    @Override
    public boolean resetPassword(String email, String passwd) {
        boolean success;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            success = mapper.updatePasswordByEmail(email, passwd);
            sqlSession.commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    @Override
    public boolean userIsExists(String email) {
        User user;
        try (SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSession()) {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            user = mapper.selectUserByEmail(email);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user != null;
    }

}
