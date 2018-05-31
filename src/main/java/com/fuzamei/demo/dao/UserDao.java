package com.fuzamei.demo.dao;

import com.fuzamei.demo.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserDao {

    User selectUserByNamePass(User user);

    void updateToken(User user);

    User findUser(String token);

    boolean createUser(User user);

    User findUserByToken(@Param("token") String token);
}
