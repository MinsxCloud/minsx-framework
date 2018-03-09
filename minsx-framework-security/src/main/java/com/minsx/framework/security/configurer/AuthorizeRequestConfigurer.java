package com.minsx.framework.security.configurer;

public class AuthorizeRequestConfigurer {

    private String[] needAuthorize = new String[]{"/**"};

    private String[] unNeedAuthorize= new String[]{"/login","/logout","/static/**"};

    private WebSecurity webSecurity;

    public AuthorizeRequestConfigurer(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }

    public AuthorizeRequestConfigurer needAuthorize(String... matchers) {
        this.needAuthorize = matchers;
        return this;
    }

    public AuthorizeRequestConfigurer unNeedAuthorize(String... matchers) {
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
