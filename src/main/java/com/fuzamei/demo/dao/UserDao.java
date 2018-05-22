package com.fuzamei.demo.dao;

import com.fuzamei.demo.model.User;

public interface UserDao {

    User selectUserByNamePass(User user);

    void updateToken(User user);

    User findUser(String token);

    boolean createUser(User user);
}
