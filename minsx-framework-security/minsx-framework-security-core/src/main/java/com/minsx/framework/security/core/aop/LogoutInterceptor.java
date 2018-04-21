package com.minsx.framework.security.core.aop;

import com.minsx.framework.security.api.configure.WebSecurity;
import com.minsx.framework.security.api.handler.LogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogoutInterceptor implements HandlerInterceptor {

    @Autowired
    WebSecurity webSecurity;

    @Autowired
    LogoutHandler logoutHandler;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return logoutHandler.logout(httpServletRequest, httpServletResponse);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logoutHandler.publishPostLogoutEvent(httpServletRequest, httpServletResponse);
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logoutHandler.publishAfterLogoutEvent(httpServletRequest, httpServletResponse);
    }
}
