package com.fuzamei.demo.controller;

import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.UserService;
import com.fuzamei.demo.utils.JWTUtils;
import com.fuzamei.demo.utils.NewResponseModel;
import com.fuzamei.demo.utils.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/TransferWater")
public class UserController {

    @Resource(name ="userServiceImpl")
    private UserService userService;


    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public ResponseData login(User user, HttpSession session,HttpServletRequest request) {
        User user1 = userService.selectUserByNamePass(user);
        ResponseData responseData = ResponseData.ok();
        if(user1 !=null){
            String token= JWTUtils.sign(user1,60L*1000L*30L);
            responseData.putDataValue("user",user1);
            responseData.putDataValue("token",token);
            return  responseData;
        }else{
            responseData =  ResponseData.customerError();
        }
        return responseData;
    }
}
