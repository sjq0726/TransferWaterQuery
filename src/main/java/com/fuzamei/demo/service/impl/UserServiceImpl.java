package com.fuzamei.demo.service.impl;

import com.fuzamei.demo.dao.UserDao;
import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.UserService;
import com.fuzamei.demo.utils.DataSourceTypeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public User selectUserByNamePass(User user) {
        DataSourceTypeManager.setCustomerType(DataSourceTypeManager.DATA_SOURCE_A);
        return userDao.selectUserByNamePass(user);
    }


}
