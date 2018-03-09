package com.minsx.framework.security.aop;

import com.minsx.framework.security.base.SecurityUser;
import com.minsx.framework.security.configurer.WebSecurity;
import com.minsx.framework.security.core.LoadSecurityUserService;
import com.minsx.framework.security.exception.AuthorizationException;
import com.minsx.framework.security.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginHandler implements HandlerInterceptor {

    @Autowired
    private WebSecurity webSecurity;

    @Autowired
    private LoadSecurityUserService loadSecurityUserService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Boolean loadSuccess = true;
        String APIUrl = webSecurity.loginConfigurer().getLoginAPIUrl();
        String pageUrl = webSecurity.loginConfigurer().getLoginPageUrl();
        Boolean A = (!APIUrl.equalsIgnoreCase(pageUrl)) && httpServletRequest.getRequestURI().equalsIgnoreCase(APIUrl);
        Boolean B = APIUrl.equalsIgnoreCase(pageUrl) && httpServletRequest.getRequestURI().equalsIgnoreCase(pageUrl) && httpServletRequest.getMethod().equalsIgnoreCase("POST");
        if (A||B) {
            login(httpServletRequest, httpServletResponse);
            loadSuccess = false;
        }
        return loadSuccess;
    }

    public void login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        try {
            SecurityUser securityUser = loadSecurityUserService.loadUser(httpServletRequest);
            System.out.println(securityUser.getUsername());
            System.out.println(securityUser.getPassword());
            System.out.println("已登录");
            ResponseUtil.responseJson(httpServletResponse, new ResponseEntity<String>("login success", HttpStatus.OK));
        } catch (AuthorizationException e) {
            System.out.println("帐号密码错误");
            ResponseUtil.responseJson(httpServletResponse, new ResponseEntity<String>(e.getMessage(), HttpStatus.valueOf(e.getStatus())));
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

