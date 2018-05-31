package com.fuzamei.demo.controller;

import com.fuzamei.demo.model.DTO.RoleDTO;
import com.fuzamei.demo.model.Role;
import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.RoleService;
import com.fuzamei.demo.service.impl.RoleServiceImpl;
import com.fuzamei.demo.service.impl.UserServiceImpl;
import com.fuzamei.demo.utils.ResponseData;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/TransferWater")
public class RoleController {

    @Resource(name = "roleServiceImpl")
    private RoleServiceImpl roleService;

    @Resource(name = "userServiceImpl")
    private UserServiceImpl userService;

    /**
     * 权限的首页
     * @param request
     * @param roleDTO 分装类
     * @param pn 页码
     * @return
     */
    @RequestMapping("/findRoleList")
    @ResponseBody
    public ResponseData findRoleList(HttpServletRequest request, RoleDTO roleDTO,Integer pn){
        //根据头文件中的token查询用户和它的权限
        String token=request.getHeader("token");
        User user=userService.findUserByToken(token);
        Role role=roleService.selectRoleByName(user.getUsername());
            //1是超级管理员
            //权限为1是则查询除了自己所有的角色
            if (role.getDegree().equals("1")) {
                ResponseData responseData = ResponseData.ok();
                roleDTO.setMainDegree("1");
                List<Role> roles = roleService.selectRole(roleDTO, pn);
                Integer countPage = roleService.countRole(request,roleDTO);
                responseData.putDataValue("countPage", countPage);
                responseData.putDataValue("data", roles);
                return responseData;
            } else if (role.getDegree().equals("2")) {
                //2是管理员
                //有超级管理员赋权，只能查询普通用户
                ResponseData responseData = ResponseData.ok();
                roleDTO.setMainDegree("2");
                List<Role> roles = roleService.selectRole(roleDTO, pn);
                Integer countPage = roleService.countRole(request,roleDTO);
                responseData.putDataValue("countPage", countPage);
                responseData.putDataValue("data", roles);
                return responseData;
            }
        ResponseData responseData=ResponseData.notFound();
        responseData=ResponseData.notFound();
        return responseData;
    }

    @RequestMapping("/selectRoleByName")
    @ResponseBody
    public ResponseData selectRoleByName(String name){
        Role role=roleService.selectRoleByName(name);
        if(role!=null){
            ResponseData responseData=ResponseData.ok();
            return responseData;
        }
        ResponseData responseData=ResponseData.notFound();
        return responseData;


    }

    @RequestMapping(value = "/updateRole",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData updateRole(Role role){
        if(roleService.updateRole(role)){
            ResponseData responseData=ResponseData.ok();
            return responseData;
        }else{
            ResponseData responseData=ResponseData.forbidden();
            return responseData;
        }
    }

}
