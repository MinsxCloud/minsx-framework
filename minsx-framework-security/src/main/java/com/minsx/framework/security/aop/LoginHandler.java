package com.minsx.framework.security.aop;

import com.alibaba.fastjson.JSON;
import com.minsx.framework.common.http.ResponseUtil;
import com.minsx.framework.security.core.Authentication;
import com.minsx.framework.security.core.AuthenticationManager;
import com.minsx.framework.security.core.SecurityUser;
import com.minsx.framework.security.configurer.WebSecurity;
import com.minsx.framework.security.core.LoadSecurityUserService;
import com.minsx.framework.security.exception.AuthorizationException;
import com.minsx.framework.security.exception.LoginUrlConfigException;
import com.minsx.framework.security.simple.AuthenticationHolder;
import com.minsx.framework.security.simple.SimpleAuthentication;
import com.minsx.framework.security.simple.SimpleAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginHandler implements HandlerInterceptor {

    @Autowired
    private WebSecurity webSecurity;

    @Autowired
    private LoadSecurityUserService loadSecurityUserService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String APIUrl = webSecurity.loginConfigurer().getLoginAPIUrl();
        String pageUrl = webSecurity.loginConfigurer().getLoginPageUrl();
        if (APIUrl.equalsIgnoreCase(pageUrl)) {
            throw new LoginUrlConfigException(500, "login ApiURL can't be same sa PageURL");
        }
        login(httpServletRequest, httpServletResponse);
        return false;
    }

    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        try {
            SecurityUser securityUser = loadSecurityUserService.loadUser(httpServletRequest);
            System.out.println(securityUser.getUsername());
            System.out.println(securityUser.getPassword());
            System.out.println("已登录");

            Authentication authentication = new SimpleAuthentication();
            authentication.setAuthenticated(true);
            authentication.setSecurityUser(securityUser);
            AuthenticationManager authenticationManager = new SimpleAuthenticationManager();
            authenticationManager.initial(securityUser.getUsername(), authentication);
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(Authentication.class.getName(), securityUser.getUsername());

            ResponseUtil.responseJson(httpServletResponse, 200, "login success");
        } catch (AuthorizationException e) {
            System.out.println("帐号密码错误");
            ResponseUtil.responseJson(httpServletResponse, e.getStatus(), e.getMessage());
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

