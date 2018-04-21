package com.minsx.framework.security.api.simple;

import com.minsx.framework.security.api.configure.AuthorizeConfigurer;
import com.minsx.framework.security.api.configure.LoginConfigurer;
import com.minsx.framework.security.api.configure.LogoutConfigurer;
import com.minsx.framework.security.api.configure.WebSecurity;

public final class WebSecurityBuilder implements WebSecurity {

    private Boolean enabled;

    private Long expiredTime;

    private LoginConfigurer loginConfigurer = new SimpleLoginConfigurer(this);

    private LogoutConfigurer logoutConfigurer = new SimpleLogoutConfigurer(this);

    private AuthorizeConfigurer authorizeConfigurer = new SimpleAuthorizeConfigurer(this);

    @Override
    public WebSecurityBuilder enabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    @Override
    public WebSecurity ExpiredTime(Long millSeconds) {
        this.expiredTime = millSeconds;
        return this;
    }

    @Override
    public Long getExpiredTime() {
        return this.expiredTime;
    }

    @Override
    public LoginConfigurer loginConfigurer() {
        return loginConfigurer;
    }

    @Override
    public LogoutConfigurer logoutConfigurer() {
        return logoutConfigurer;
    }

    @Override
    public AuthorizeConfigurer authorizeConfigurer() {
        return authorizeConfigurer;
    }

    @Override
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
