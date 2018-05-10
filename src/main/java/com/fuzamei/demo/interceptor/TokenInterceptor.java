package com.fuzamei.demo.interceptor;

import com.fuzamei.demo.model.User;
import com.fuzamei.demo.utils.JWTUtils;
import com.fuzamei.demo.utils.NewResponseModel;
import com.fuzamei.demo.utils.ResponseData;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class TokenInterceptor  implements HandlerInterceptor {

    //拦截每个请求
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        ResponseData responseData = ResponseData.ok();
        //token不存在
        if (null != token) {
            User user=JWTUtils.unsign(token,User.class);
            if(null !=user){
                return true;
            }else{
                ResponseData.forbidden();
                return false;
            }
        }
        ResponseData.forbidden();
        return false;
    }



    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
