package com.fuzamei.demo.controller;

import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Resource(name ="userServiceImpl")
    private UserService userService;


    @RequestMapping("/login")
    public ModelAndView login(User user, HttpSession session){
        User user1=userService.selectUserByNamePass(user);
        if(user1 !=null){
            ModelAndView mav=new ModelAndView("index");
            session.setAttribute("user",user1);
            session.setMaxInactiveInterval(60*60*24*7);

            return mav;
        }else {
            ModelAndView mav = new ModelAndView("login");
            return mav;
        }
    }

    @RequestMapping("/exit")
    public ModelAndView exit(HttpSession session,ModelAndView mav){
        session.invalidate();
        mav.setViewName("redirect:login");
        return mav;
    }
}
