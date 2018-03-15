package com.minsx.framework.security.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.minsx.framework.common.basic.StringUtil;
import com.minsx.framework.common.basic.ThreadLocalUtil;
import com.minsx.framework.security.core.Authentication;
import com.minsx.framework.security.core.AuthenticationManager;
import com.minsx.framework.security.core.SecurityUser;
import com.minsx.framework.security.core.RequestAuthorize;
import com.minsx.framework.security.exception.AuthorizationException;
import com.minsx.framework.security.simple.AuthenticationHolder;
import com.minsx.framework.security.simple.SimpleAuthenticationManager;
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
        Object object = session.getAttribute(Authentication.class.getName());
        if (object == null) {
            throw new AuthorizationException(403, "unauthorized");
        }
        ThreadLocalUtil.put(Authentication.class.getName(), object.toString());
        AuthenticationManager manager = new SimpleAuthenticationManager();
        Authentication authentication = manager.get(object.toString());
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
        if ("ALL".equalsIgnoreCase(paramsOfAuthorizes)) {
            return true;
        }
        if (paramsOfRequest != null && paramsOfAuthorizes != null) {
            System.out.println("requestParams = " + JSON.toJSONString(paramsOfRequest));
            System.out.println("authorizesParams = " + JSON.toJSONString(paramsOfAuthorizes));
            Map<String, String> params = StringUtil.parseUrlParams(paramsOfAuthorizes);
            for (Map.Entry<String, String> kv : params.entrySet()) {
                String[] value = paramsOfRequest.get(kv.getKey());
                if (value != null && value.length >= 1 && (!value[0].equalsIgnoreCase(kv.getValue()))) {
                    return false;
                }
            }
            return true;
        } else if (paramsOfRequest == null && paramsOfAuthorizes == null) {
            return true;
        } else {
            return false;
        }
    }
}
