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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfiguration {

    @Value("${minsx.security.enabled:}")
    private Boolean enabled;

    @Value("${minsx.security.authorize.needAuthorize:}")
    private String[] authorizeNeedAuthorize;

    @Value("${minsx.security.authorize.unNeedAuthorize:}")
    private String[] authorizeUnNeedAuthorize;

    @Value("${minsx.security.login.APIUrl:}")
    private String loginAPIUrl;

    @Value("${minsx.security.login.pageUrl:}")
    private String loginPageUrl;

    @Value("${minsx.security.logout.APIUrl:}")
    private String logoutAPIUrl;

    @Value("${minsx.security.logout.clearAuthentication:}")
    private Boolean logoutClearAuthentication;

    @Value("${minsx.security.logout.invalidateHttpSession:}")
    private Boolean logoutInvalidateHttpSession;

    @Value("${minsx.security.logout.deleteCookies:}")
    private Boolean logoutDeleteCookies;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String[] getAuthorizeNeedAuthorize() {
        return authorizeNeedAuthorize;
    }

    public void setAuthorizeNeedAuthorize(String[] authorizeNeedAuthorize) {
        this.authorizeNeedAuthorize = authorizeNeedAuthorize;
    }

    public String[] getAuthorizeUnNeedAuthorize() {
        return authorizeUnNeedAuthorize;
    }

    public void setAuthorizeUnNeedAuthorize(String[] authorizeUnNeedAuthorize) {
        this.authorizeUnNeedAuthorize = authorizeUnNeedAuthorize;
    }

    public String getLoginAPIUrl() {
        return loginAPIUrl;
    }

    public void setLoginAPIUrl(String loginAPIUrl) {
        this.loginAPIUrl = loginAPIUrl;
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public void setLoginPageUrl(String loginPageUrl) {
        this.loginPageUrl = loginPageUrl;
    }

    public String getLogoutAPIUrl() {
        return logoutAPIUrl;
    }

    public void setLogoutAPIUrl(String logoutAPIUrl) {
        this.logoutAPIUrl = logoutAPIUrl;
    }

    public Boolean getLogoutClearAuthentication() {
        return logoutClearAuthentication;
    }

    public void setLogoutClearAuthentication(Boolean logoutClearAuthentication) {
        this.logoutClearAuthentication = logoutClearAuthentication;
    }

    public Boolean getLogoutInvalidateHttpSession() {
        return logoutInvalidateHttpSession;
    }

    public void setLogoutInvalidateHttpSession(Boolean logoutInvalidateHttpSession) {
        this.logoutInvalidateHttpSession = logoutInvalidateHttpSession;
    }

    public Boolean getLogoutDeleteCookies() {
        return logoutDeleteCookies;
    }

    public void setLogoutDeleteCookies(Boolean logoutDeleteCookies) {
        this.logoutDeleteCookies = logoutDeleteCookies;
    }
}
