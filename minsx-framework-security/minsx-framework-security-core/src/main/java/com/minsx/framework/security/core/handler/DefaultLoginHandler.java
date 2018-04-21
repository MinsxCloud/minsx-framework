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

import com.minsx.framework.common.http.ResponseUtil;
import com.minsx.framework.security.api.authentication.Authentication;
import com.minsx.framework.security.api.authentication.AuthenticationManager;
import com.minsx.framework.security.api.basic.SecurityUser;
import com.minsx.framework.security.api.configure.WebSecurity;
import com.minsx.framework.security.api.exception.AuthorizationException;
import com.minsx.framework.security.api.handler.LoginHandler;
import com.minsx.framework.security.api.listener.*;
import com.minsx.framework.security.api.service.LoadSecurityUserService;
import com.minsx.framework.security.api.simple.SimpleAuthentication;
import com.minsx.framework.security.api.token.UserTokenManager;
import com.minsx.framework.security.core.aop.LoginInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DefaultLoginHandler implements LoginHandler {

    private final static Log LOG = LogFactory.getLog(LoginInterceptor.class);

    @Autowired
    private WebSecurity webSecurity;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoadSecurityUserService loadSecurityUserService;

    @Autowired
    private ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    private UserTokenManager userTokenManager;

    @Override
    public Boolean login(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        try {
            SecurityUser securityUser = loadSecurityUserService.loadUser(httpServletRequest);
            LOG.info("the user:" + securityUser.getUsername() + " login success");
            Authentication authentication = new SimpleAuthentication();
            authentication.setAuthenticated(true);
            authentication.setSecurityUser(securityUser);

            authenticationManager.initial(securityUser.getUsername(), authentication);

            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(Authentication.class.getName(), securityUser.getUsername());

            configurableApplicationContext.publishEvent(new LoginSuccessEvent(authentication, httpServletRequest, httpServletResponse));
            userTokenManager.login(session.getId(), securityUser.getUsername());

            ResponseUtil.responseJson(httpServletResponse, 200, "login success");
        } catch (AuthorizationException e) {
            configurableApplicationContext.publishEvent(new LoginFailedEvent(e.getMessage(), httpServletRequest, httpServletResponse));
            throw e;
        }
        return false;
    }

    @Override
    public void publishPreLoginEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new LoginPrepareEvent(httpServletRequest, httpServletResponse));
    }

    @Override
    public void publishPostLoginEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new LoginPostEvent(httpServletRequest, httpServletResponse));
    }

    @Override
    public void publishAfterLoginEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new LoginAfterEvent(httpServletRequest, httpServletResponse));
    }
}
