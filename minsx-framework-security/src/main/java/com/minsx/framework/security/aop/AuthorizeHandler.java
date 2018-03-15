package com.minsx.framework.security.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.minsx.framework.security.core.Authentication;
import com.minsx.framework.security.core.SecurityUser;
import com.minsx.framework.security.core.RequestAuthorize;
import com.minsx.framework.security.exception.AuthorizationException;
import com.minsx.framework.security.simple.AuthenticationHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Component
public class AuthorizeHandler implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //检测是否登录,未登录进行跳转
        System.out.println(httpServletRequest.getRequestURI());
        HttpSession session = httpServletRequest.getSession();
        Authentication authentication = (Authentication) session.getAttribute(Authentication.class.getName());
        AuthenticationHolder.put(authentication);
        if (authentication != null && authentication.isAuthenticated() && authentication.getSecurityUser() != null) {
            SecurityUser securityUser = authentication.getSecurityUser();
            List<RequestAuthorize> requestAuthorizes = securityUser.getRequestAuthorizes();
            boolean matched = requestAuthorizes.stream().anyMatch(u ->
                    matchedURI(httpServletRequest.getRequestURI(), u.getURI()) &&
                            matchedMethod(httpServletRequest.getMethod(), u.getMethod()) &&
                            matchedParams(httpServletRequest.getParameterMap(), u.getParams())
            );
            if (matched) {
                return true;
            } else {
                throw new AuthorizationException(403, "unauthorized");
            }
        } else {
            throw new AuthorizationException(403, "unauthorized");
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    public boolean matchedURI(String requestURI, String authorizeURI) {
        return requestURI.equalsIgnoreCase(authorizeURI) || authorizeURI.equalsIgnoreCase("ALL");
    }

    public boolean matchedMethod(String requestMethod, String authorizeMethod) {
        return requestMethod.equalsIgnoreCase(authorizeMethod) || authorizeMethod.equalsIgnoreCase("ALL");
    }

    public boolean matchedParams(Map<String, String[]> paramsOfRequest, String paramsOfAuthorizes) {
        JSONObject requestParams = JSON.parseObject(paramsOfAuthorizes);
        JSONObject authorizesParams = JSON.parseObject(paramsOfAuthorizes);
        System.out.println("requestParams=" + requestParams.toJSONString());
        System.out.println("authorizesParams=" + authorizesParams.toJSONString());
        return requestParams.equals(authorizesParams) || paramsOfAuthorizes.equalsIgnoreCase("ALL");
    }
}
