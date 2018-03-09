package com.minsx.framework.security.configurer;

import org.springframework.stereotype.Component;

@Component
public class LogoutConfigurer {

    private Boolean clearAuthentication = true;

    private Boolean deleteCookies = true;

    private Boolean invalidateHttpSession = true;

    private String logoutUrl = "/logout";

    private String logoutSuccessUrl = "/login";

    private WebSecurity webSecurity;

    public LogoutConfigurer(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }

    public LogoutConfigurer clearAuthentication() {
        this.clearAuthentication = true;
        return this;
    }

    public LogoutConfigurer deleteCookies() {
        this.deleteCookies = true;
        return this;
    }

    public LogoutConfigurer invalidateHttpSession() {
        this.invalidateHttpSession = true;
        return this;
    }

    public LogoutConfigurer logoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
        return this;
    }

    public LogoutConfigurer logoutSuccessUrl(String logoutSuccessUrl) {
        this.logoutSuccessUrl = logoutSuccessUrl;
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

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public String getLogoutSuccessUrl() {
        return logoutSuccessUrl;
    }

    public WebSecurity getWebSecurity() {
        return webSecurity;
    }

    public void setWebSecurity(WebSecurity webSecurity) {
        this.webSecurity = webSecurity;
    }
}
