package com.minsx.framework.security.api.simple;

import com.minsx.framework.security.api.configure.AuthorizeConfigurer;
import com.minsx.framework.security.api.configure.WebSecurity;

public class SimpleAuthorizeConfigurer implements AuthorizeConfigurer{

    private String[] needAuthorize = new String[]{"/**"};

    private String[] unNeedAuthorize = new String[]{"/login", "/logoutByToken", "/static/**"};

    private WebSecurity webSecurity;

    public SimpleAuthorizeConfigurer(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }

    public AuthorizeConfigurer needAuthorize(String... matchers) {
        this.needAuthorize = matchers;
        return this;
    }

    public AuthorizeConfigurer unNeedAuthorize(String... matchers) {
        this.unNeedAuthorize = matchers;
        return this;
    }

    public WebSecurity and() {
        return this.webSecurity;
    }

    public String[] getNeedAuthorize() {
        return needAuthorize;
    }

    public String[] getUnNeedAuthorize() {
        return unNeedAuthorize;
    }

    public WebSecurity getWebSecurity() {
        return webSecurity;
    }

    public void setWebSecurity(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }
}
