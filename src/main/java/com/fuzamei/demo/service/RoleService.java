package com.fuzamei.demo.service;

import com.fuzamei.demo.model.DTO.RoleDTO;
import com.fuzamei.demo.model.Role;
import org.apache.ibatis.annotations.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleService {

    List<Role> selectRole(RoleDTO roleDTO, Integer pn);

    Integer countRole(HttpServletRequest request,RoleDTO roleDTO);

    Role selectRoleByName(String name);

    boolean updateRole(Role role);

    boolean addRole(Role role);
}
