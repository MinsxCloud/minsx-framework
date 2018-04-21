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
import com.minsx.framework.security.api.configure.WebSecurity;
import com.minsx.framework.security.api.handler.LogoutHandler;
import com.minsx.framework.security.api.listener.LogoutAfterEvent;
import com.minsx.framework.security.api.listener.LogoutPostEvent;
import com.minsx.framework.security.api.listener.LogoutPrepareEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DefaultLogoutHandler implements LogoutHandler {

    @Autowired
    private WebSecurity webSecurity;

    @Autowired
    ConfigurableApplicationContext configurableApplicationContext;

    @Override
    public Boolean logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        String logoutUrl = webSecurity.logoutConfigurer().getLogoutAPIUrl();
        if (httpServletRequest.getRequestURI().equalsIgnoreCase(logoutUrl)) {
            System.out.println("已退出登录");
        }
        ResponseUtil.responseJson(httpServletResponse, 200, "logout success");
        return false;
    }

    @Override
    public void publishPreLogoutEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new LogoutPrepareEvent(httpServletRequest,httpServletResponse));
    }

    @Override
    public void publishPostLogoutEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new LogoutPostEvent(httpServletRequest,httpServletResponse));
    }

    @Override
    public void publishAfterLogoutEvent(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        configurableApplicationContext.publishEvent(new LogoutAfterEvent(httpServletRequest,httpServletResponse));
    }
}
