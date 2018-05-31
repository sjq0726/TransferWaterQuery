package com.fuzamei.demo.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.fuzamei.demo.model.User;
import com.fuzamei.demo.service.UserService;
import com.fuzamei.demo.utils.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor  implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);

    @Autowired
    private UserService userService;


    //拦截每个请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("对用户的token信息进行校验");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        ResponseData responseData = ResponseData.ok();
        //token存在
        if (!(StringUtils.isEmpty(token))) {
            User user=userService.findUser(token);
            //User user=JWTUtils.unsign(token,User.class);
            if(null !=user){
                return true;
            }else{
                return false;
            }
        }
        return false;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
