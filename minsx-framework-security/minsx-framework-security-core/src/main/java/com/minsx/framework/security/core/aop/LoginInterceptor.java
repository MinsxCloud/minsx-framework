package com.minsx.framework.security.core.aop;

import com.minsx.framework.common.http.ResponseUtil;
import com.minsx.framework.security.api.handler.LoginHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    private final static Log LOG = LogFactory.getLog(LoginInterceptor.class);

    @Autowired
    LoginHandler loginHandler;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        ResponseEntity<?> response = loginHandler.handle(httpServletRequest);
        ResponseUtil.responseJson(httpServletResponse, response.getStatusCodeValue(), response.getBody());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}

