package com.minsx.framework.security.api.simple;

import com.minsx.framework.security.api.configure.LoginConfigurer;
import com.minsx.framework.security.api.configure.WebSecurity;

public class SimpleLoginConfigurer implements LoginConfigurer{

    private String loginAPIUrl = "/api/login";

    private String loginPageUrl = "/login";

    private WebSecurity webSecurity;

    public SimpleLoginConfigurer(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }

    public SimpleLoginConfigurer loginAPIUrl(String loginAPIUrl) {
        this.loginAPIUrl = loginAPIUrl;
        return this;
    }

    public SimpleLoginConfigurer loginPageUrl(String loginPageUrl) {
        this.loginPageUrl = loginPageUrl;
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

    public WebSecurity getWebSecurity() {
        return webSecurity;
    }

    public void setWebSecurity(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }
}
