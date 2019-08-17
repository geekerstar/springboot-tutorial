package com.geekerstar.interceptor;

import com.geekerstar.entity.User;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author geekerstar
 * date: 2019-08-17 10:04
 * description:
 */
public class LoginValidationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle ...");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            // 若未登录，重定向到登录页面
            response.sendRedirect("login.html");
            return false;
        }
        // 若已登录，继续往下执行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle ...");
        // com.geekerstar.controller 方法调用完毕后，执行此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion ...");
        // 页面渲染完成后调用此方法, 一般用来清除某些资源等
    }
}
