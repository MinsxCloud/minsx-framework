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
package com.minsx.framework.security.core.configuration;

import com.minsx.framework.security.api.configure.WebSecurity;
import com.minsx.framework.security.api.configure.WebSecurityConfigurer;
import com.minsx.framework.security.api.exception.LoginUrlConfigException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SecurityConfigurationAdapt {

    @Autowired
    WebSecurity webSecurity;

    @Autowired
    WebSecurityConfigurer webSecurityConfigurer;

    @Autowired
    SecurityConfiguration configuration;

    @PostConstruct
    public void initializeConfiguration() {
        webSecurityConfigurer.configure(webSecurity);
        if (webSecurity.loginConfigurer().getLoginAPIUrl().equals(webSecurity.loginConfigurer().getLoginPageUrl())) {
            throw new LoginUrlConfigException("login ApiURL can't be same sa PageURL");
        }

        /*System.out.println("---->" + webSecurity.getEnabled() + "--->" + configuration.getEnabled());
        System.out.println("+++++++>"+webSecurity.loginConfigurer().getLoginAPIUrl()+"+++++>"+configuration.getLoginAPIUrl().equals(null));
        webSecurity.enabled(configuration.getEnabled() == null ? webSecurity.getEnabled() : configuration.getEnabled());
        webSecurity.loginConfigurer().loginAPIUrl(configuration.getLoginAPIUrl() == null ? webSecurity.loginConfigurer().getLoginAPIUrl() : configuration.getLoginAPIUrl());
        webSecurity.loginConfigurer().loginPageUrl(configuration.getLoginPageUrl() == null ? webSecurity.loginConfigurer().getLoginPageUrl() : configuration.getLoginPageUrl());
        webSecurity.logoutConfigurer().logoutAPIUrl(configuration.getLogoutAPIUrl() == null ? webSecurity.logoutConfigurer().getLogoutAPIUrl() : configuration.getLogoutAPIUrl());
        webSecurity.logoutConfigurer().clearAuthentication(configuration.getLogoutClearAuthentication() == null ? webSecurity.logoutConfigurer().getClearAuthentication() : configuration.getLogoutClearAuthentication());
        webSecurity.logoutConfigurer().invalidateHttpSession(configuration.getLogoutInvalidateHttpSession() == null ? webSecurity.logoutConfigurer().getInvalidateHttpSession() : configuration.getLogoutInvalidateHttpSession());
        webSecurity.logoutConfigurer().deleteCookies(configuration.getLogoutDeleteCookies() == null ? webSecurity.logoutConfigurer().getDeleteCookies() : configuration.getLogoutDeleteCookies());
        webSecurity.authorizeConfigurer().needAuthorize(configuration.getAuthorizeNeedAuthorize() == null ? webSecurity.authorizeConfigurer().getNeedAuthorize() : configuration.getAuthorizeNeedAuthorize());
        webSecurity.authorizeConfigurer().unNeedAuthorize(configuration.getAuthorizeUnNeedAuthorize() == null ? webSecurity.authorizeConfigurer().getUnNeedAuthorize() : configuration.getAuthorizeUnNeedAuthorize());
        System.out.println("=---->" + webSecurity.getEnabled() + "=--->" + configuration.getEnabled());
        System.out.println("+++++++>"+webSecurity.loginConfigurer().getLoginAPIUrl()+"+++++>"+configuration.getLoginAPIUrl().equals(null));
*/
    }


}
