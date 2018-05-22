package com.fuzamei.demo.service;

import com.fuzamei.demo.model.User;

public interface UserService {

     User selectUserByNamePass(User user);

     void updateToken(User user);

     User findUser(String token);

     boolean createUser(User user);
}
