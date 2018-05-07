package com.fuzamei.demo.dao;

import com.fuzamei.demo.model.User;

public interface UserDao {

    User selectUserByNamePass(User user);
}
