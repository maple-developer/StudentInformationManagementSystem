package com.maple.mapper;

import com.maple.entities.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User selectUserByUserName(@Param("username") String username);

    User selectUserByEmail(@Param("email") String email);

    User selectUserByEmailAndPassword(@Param("email") String email, @Param("passwd") String passwd);

    User selectUserByUserNameAndPassword(@Param("username") String username, @Param("passwd") String passwd);

    boolean register(User user);

    boolean updatePasswordByEmail(@Param("email") String email, @Param("passwd") String passwd);
}
