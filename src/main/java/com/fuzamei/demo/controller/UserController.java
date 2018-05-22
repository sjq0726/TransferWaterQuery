package com.fuzamei.demo.controller;

import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.UserService;
import com.fuzamei.demo.utils.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping("/TransferWater")
public class UserController {

    @Resource(name ="userServiceImpl")
    private UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData login(User user, HttpSession session,HttpServletRequest request) {
        User user1 = userService.selectUserByNamePass(user);
        Long deadline=1000*60*60*24*3L;
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("deadline",deadline);
        if(user1 !=null){
            //String token=JWTUtils.sign(user1,deadline);
            String token = UUID.randomUUID().toString().replaceAll("-", "");
            user1.setToken(token);
            userService.updateToken(user1);
            responseData.putDataValue("message","登录成功");
            responseData.putDataValue("user",user1);
            responseData.putDataValue("token",token);
            return  responseData;
        }else{
            responseData =  ResponseData.notFound();
            responseData.putDataValue("message","登录失败");
        }
        return responseData;
    }

    @RequestMapping(value = "/createUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseData createUser(User user){
        User user1=userService.selectUserByNamePass(user);
        if(user1 ==null) {
            Boolean result = userService.createUser(user);
            if (result) {
                ResponseData responseData = ResponseData.ok();
                responseData.putDataValue("message","注册成功");
                return responseData;
            }
            ResponseData responseData = ResponseData.badRequest();
            responseData.putDataValue("message","注册失败");
            return responseData;
        }
        ResponseData responseData = ResponseData.forbidden();
        responseData.putDataValue("message","该用户已经存在");
        return responseData;
    }


}
