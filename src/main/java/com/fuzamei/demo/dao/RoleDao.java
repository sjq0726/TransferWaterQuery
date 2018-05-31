package com.fuzamei.demo.dao;

import com.fuzamei.demo.model.DTO.RoleDTO;
import com.fuzamei.demo.model.Role;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RoleDao {

    List<Role> selectRole(RoleDTO roleDTO);

    Integer countRole(RoleDTO roleDTO);

    Role selectRoleByName(@Param("name")String name);

    boolean updateRole(Role role);

    boolean addRole(Role role);
}
