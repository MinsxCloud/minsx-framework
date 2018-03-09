package com.minsx.framework.security.configurer;

import org.springframework.stereotype.Component;

@Component
public class LoginConfigurer {

    private String loginAPIUrl = "/login";

    private String loginPageUrl = "/login";

    private String loginSuccessUrl = "/";

    private String loginFailureUrl = "/login";

    private WebSecurity webSecurity;

    public LoginConfigurer(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }

    public LoginConfigurer loginAPIUrl(String loginAPIUrl) {
        this.loginAPIUrl = loginAPIUrl;
        return this;
    }

    public LoginConfigurer loginPageUrl(String loginPageUrl) {
        this.loginPageUrl = loginPageUrl;
        return this;
    }

    public LoginConfigurer loginSuccessUrl(String loginSuccessUrl) {
        this.loginSuccessUrl = loginSuccessUrl;
        return this;
    }

    public LoginConfigurer loginFailureUrl(String loginFailureUrl) {
        this.loginFailureUrl = loginFailureUrl;
        return this;
    }

    public WebSecurity and() {
        return this.webSecurity;
    }

    public String getLoginAPIUrl() {
        return loginAPIUrl;
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }

    public String getLoginSuccessUrl() {
        return loginSuccessUrl;
    }

    public String getLoginFailureUrl() {
        return loginFailureUrl;
    }

    public WebSecurity getWebSecurity() {
        return webSecurity;
    }

    public void setWebSecurity(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }
}
