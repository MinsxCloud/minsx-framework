/*
 *
 *  * Copyright 2017 https://www.minsx.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.minsx.framework.security.core.handler;

import com.alibaba.fastjson.JSON;
import com.minsx.framework.common.basic.StringUtil;
import com.minsx.framework.common.basic.ThreadLocalUtil;
import com.minsx.framework.security.api.authentication.Authentication;
import com.minsx.framework.security.api.authentication.AuthenticationManager;
import com.minsx.framework.security.api.authority.RequestAuthority;
import com.minsx.framework.security.api.basic.SecurityUser;
import com.minsx.framework.security.api.exception.AuthorizationException;
import com.minsx.framework.security.api.handler.AuthorizeHandler;
import com.minsx.framework.security.api.listener.AuthorizeAfterEvent;
import com.minsx.framework.security.api.listener.AuthorizePostEvent;
import com.minsx.framework.security.api.listener.AuthorizePrepareEvent;
import com.minsx.framework.security.api.simple.SimpleAuthenticationManager;
import com.minsx.framework.security.core.aop.LoginInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public class DefaultAuthorizeHandler implements AuthorizeHandler {

    private final static Log LOG = LogFactory.getLog(LoginInterceptor.class);

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public Boolean authorize(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
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
    public void publishPreAuthorizeEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new AuthorizePrepareEvent(httpServletRequest, httpServletResponse));
    }

    @Override
    public void publishPostAuthorizeEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new AuthorizePostEvent(httpServletRequest, httpServletResponse));
    }

    @Override
    public void publishAfterAuthorizeEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new AuthorizeAfterEvent(httpServletRequest, httpServletResponse));
    }

    private boolean matchedURI(String requestURI, String authorizeURI) {
        return requestURI.equalsIgnoreCase(authorizeURI) || authorizeURI.equalsIgnoreCase("ALL");
    }

    private boolean matchedMethod(String requestMethod, String authorizeMethod) {
        return requestMethod.equalsIgnoreCase(authorizeMethod) || authorizeMethod.equalsIgnoreCase("ALL");
    }

    private boolean matchedParams(Map<String, String[]> paramsOfRequest, String paramsOfAuthorizes) {
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
