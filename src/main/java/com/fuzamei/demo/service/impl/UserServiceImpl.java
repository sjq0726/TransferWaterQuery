package com.fuzamei.demo.service.impl;

import com.fuzamei.demo.dao.UserDao;
import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User selectUserByNamePass(User user) {
        return userDao.selectUserByNamePass(user);
    }


}
