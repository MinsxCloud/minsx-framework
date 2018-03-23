package com.minsx.framework.security.api.simple;

import com.minsx.framework.security.api.configure.AuthorizeConfigurer;
import com.minsx.framework.security.api.configure.LoginConfigurer;
import com.minsx.framework.security.api.configure.LogoutConfigurer;
import com.minsx.framework.security.api.configure.WebSecurity;

public final class WebSecurityBuilder implements WebSecurity {

    private Boolean enabled;

    private LoginConfigurer loginConfigurer = new SimpleLoginConfigurer(this);

    private LogoutConfigurer logoutConfigurer = new SimpleLogoutConfigurer(this);

    private AuthorizeConfigurer authorizeConfigurer = new SimpleAuthorizeConfigurer(this);

    public WebSecurityBuilder enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public LoginConfigurer loginConfigurer() {
        return loginConfigurer;
    }

    public LogoutConfigurer logoutConfigurer() {
        return logoutConfigurer;
    }

    public AuthorizeConfigurer authorizeConfigurer() {
        return authorizeConfigurer;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public LoginConfigurer getLoginConfigurer() {
        return loginConfigurer;
    }

    public LogoutConfigurer getLogoutConfigurer() {
        return logoutConfigurer;
    }

    public AuthorizeConfigurer getAuthorizeConfigurer() {
        return authorizeConfigurer;
    }
}
