package com.fuzamei.demo.controller;

import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.UserService;
import com.fuzamei.demo.utils.NewResponseModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/TransferWater")
@CrossOrigin
public class UserController {

    @Resource(name ="userServiceImpl")
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    public NewResponseModel login(User user, HttpSession session,HttpServletRequest request){
        System.out.println("1...............");
        User user1=userService.selectUserByNamePass(user);
        if(user1 !=null){
            NewResponseModel newResponseModel=new NewResponseModel(200,"登录成功");
            session.setAttribute("user",user1);
            session.setMaxInactiveInterval(60*60*24*7);
            return newResponseModel;
        }else {
            NewResponseModel newResponseModel=new NewResponseModel(400,"登录失败");
            return newResponseModel;
        }
    }
}
