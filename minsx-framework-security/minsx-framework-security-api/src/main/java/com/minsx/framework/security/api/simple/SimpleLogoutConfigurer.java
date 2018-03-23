package com.minsx.framework.security.api.simple;

import com.minsx.framework.security.api.configure.LogoutConfigurer;
import com.minsx.framework.security.api.configure.WebSecurity;

public class SimpleLogoutConfigurer implements LogoutConfigurer {

    private Boolean clearAuthentication = true;

    private Boolean deleteCookies = true;

    private Boolean invalidateHttpSession = true;

    private String logoutAPIUrl = "/logout";

    private WebSecurity webSecurity;

    public SimpleLogoutConfigurer(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }

    @Override
    public LogoutConfigurer clearAuthentication(Boolean clear) {
        this.clearAuthentication = clear;
        return this;
    }

    @Override
    public LogoutConfigurer deleteCookies(Boolean delete) {
        this.deleteCookies = delete;
        return this;
    }

    @Override
    public LogoutConfigurer invalidateHttpSession(Boolean invalidate) {
        this.invalidateHttpSession = invalidate;
        return this;
    }

    public SimpleLogoutConfigurer logoutAPIUrl(String logoutUrl) {
        this.logoutAPIUrl = logoutUrl;
        return this;
    }

    public WebSecurity and() {
        return this.webSecurity;
    }

    public Boolean getClearAuthentication() {
        return clearAuthentication;
    }

    public Boolean getDeleteCookies() {
        return deleteCookies;
    }

    public Boolean getInvalidateHttpSession() {
        return invalidateHttpSession;
    }

    public String getLogoutAPIUrl() {
        return logoutAPIUrl;
    }

    public WebSecurity getWebSecurity() {
        return webSecurity;
    }

    public void setWebSecurity(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }
}
