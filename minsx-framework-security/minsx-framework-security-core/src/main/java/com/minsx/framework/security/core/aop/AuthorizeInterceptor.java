package com.minsx.framework.security.core.aop;

import com.alibaba.fastjson.JSON;
import com.minsx.framework.common.basic.StringUtil;
import com.minsx.framework.common.basic.ThreadLocalUtil;
import com.minsx.framework.security.api.authentication.Authentication;
import com.minsx.framework.security.api.authentication.AuthenticationManager;
import com.minsx.framework.security.api.authority.RequestAuthority;
import com.minsx.framework.security.api.basic.SecurityUser;
import com.minsx.framework.security.api.exception.AuthorizationException;
import com.minsx.framework.security.api.simple.SimpleAuthenticationManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class AuthorizeInterceptor implements HandlerInterceptor {

    private final static Log LOG = LogFactory.getLog(LoginInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //检测是否登录
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
            List<RequestAuthority> requestAuthorizes = securityUser.getRequestAuthorities();
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
            LOG.debug("request params: " + JSON.toJSONString(paramsOfRequest));
            LOG.debug("authorizes params: " + JSON.toJSONString(paramsOfAuthorizes));
            Map<String, String> params = StringUtil.parseUrlParams(paramsOfAuthorizes);
            for (Map.Entry<String, String> kv : params.entrySet()) {
                String[] value = paramsOfRequest.get(kv.getKey());
                if (value != null && value.length >= 1 && (!value[0].equals(kv.getValue()))) {
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
