package com.fuzamei.demo.service.impl;

import com.fuzamei.demo.dao.RoleDao;
import com.fuzamei.demo.dao.UserDao;
import com.fuzamei.demo.model.DTO.RoleDTO;
import com.fuzamei.demo.model.Role;
import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.RoleService;
import com.fuzamei.demo.utils.ResponseData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;


    @Override
    public List<Role> selectRole(RoleDTO roleDTO, Integer pn) {
        if (roleDTO.getMainDegree().equals("1")) {
            Page<Role> roles = PageHelper.offsetPage(pn * 10, 10).doSelectPage(
                    () -> roleDao.selectRole(roleDTO)
            );
            return roles;
        } else if (roleDTO.getMainDegree().equals("2")) {
            Page<Role> roles = PageHelper.offsetPage(pn * 10, 10).doSelectPage(
                    () -> roleDao.selectRole(roleDTO)
            );
            return roles;
        }
        return null;
    }

    @Override
    public Integer countRole(HttpServletRequest request, RoleDTO roleDTO) {
        String token=request.getHeader("token");
        User user=userDao.findUserByToken(token);
        Role role=roleDao.selectRoleByName(user.getUsername());
        if(role.getDegree().equals("1")){
            return roleDao.countRole(roleDTO);
        }else if(role.getDegree().equals("2")){
            return roleDao.countRole(roleDTO);
        }
        return null;

    }

    @Override
    public Role selectRoleByName(String name) {
        return roleDao.selectRoleByName(name);
    }

    @Override
    public boolean updateRole(Role role) {
       return roleDao.updateRole(role);
    }

    @Override
    public boolean addRole(Role role) {
        return roleDao.addRole(role);
    }
}
