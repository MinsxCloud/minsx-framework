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

import com.mashape.unirest.http.HttpResponse;
import com.minsx.framework.common.http.ResponseUtil;
import com.minsx.framework.security.api.authentication.Authentication;
import com.minsx.framework.security.api.authentication.AuthenticationManager;
import com.minsx.framework.security.api.basic.SecurityUser;
import com.minsx.framework.security.api.configure.WebSecurity;
import com.minsx.framework.security.api.exception.AuthorizationException;
import com.minsx.framework.security.api.handler.LoginHandler;
import com.minsx.framework.security.api.listener.LoginFailedEvent;
import com.minsx.framework.security.api.listener.LoginSuccessEvent;
import com.minsx.framework.security.api.service.LoadSecurityUserService;
import com.minsx.framework.security.api.simple.SimpleAuthentication;
import com.minsx.framework.security.api.simple.SimpleAuthenticationManager;
import com.minsx.framework.security.api.token.UserTokenManager;
import com.minsx.framework.security.core.aop.LoginInterceptor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DefaultLoginHandler implements LoginHandler {

    private final static Log LOG = LogFactory.getLog(LoginInterceptor.class);

    @Autowired
    private WebSecurity webSecurity;

    @Autowired
    private LoadSecurityUserService loadSecurityUserService;

    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;

    @Autowired
    UserTokenManager userTokenManager;

    @Override
    public ResponseEntity<?> handle(HttpServletRequest httpServletRequest) {
        ResponseEntity<?> response = new ResponseEntity<Object>("login success", HttpStatus.OK);
        try {
            SecurityUser securityUser = loadSecurityUserService.loadUser(httpServletRequest);
            LOG.info("the user:" + securityUser.getUsername() + " login success");
            Authentication authentication = new SimpleAuthentication();
            authentication.setAuthenticated(true);
            authentication.setSecurityUser(securityUser);

            AuthenticationManager authenticationManager = new SimpleAuthenticationManager();
            authenticationManager.initial(securityUser.getUsername(), authentication);

            HttpSession session = httpServletRequest.getSession();
            session.setAttribute(Authentication.class.getName(), securityUser.getUsername());

            configurableApplicationContext.publishEvent(new LoginSuccessEvent(authentication, httpServletRequest));
            userTokenManager.login(session.getId(), securityUser.getUsername());

        } catch (AuthorizationException e) {
            configurableApplicationContext.publishEvent(new LoginFailedEvent(e.getMessage(), httpServletRequest));
            response = new ResponseEntity<Object>(e.getMessage(), HttpStatus.valueOf(e.getStatus()));
        }
        return response;
    }

}
