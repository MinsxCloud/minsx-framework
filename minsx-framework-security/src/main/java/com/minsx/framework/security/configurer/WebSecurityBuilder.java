package com.minsx.framework.security.configurer;

import org.springframework.stereotype.Component;

@Component
public final class WebSecurityBuilder implements WebSecurity {

    private Boolean enabled;

    private LoginConfigurer loginConfigurer = new LoginConfigurer(this);

    private LogoutConfigurer logoutConfigurer = new LogoutConfigurer(this);

    private AuthorizeRequestConfigurer authorizeRequestConfigurer = new AuthorizeRequestConfigurer(this);

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

    public AuthorizeRequestConfigurer authorizeConfigurer() {
        return authorizeRequestConfigurer;
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

    public AuthorizeRequestConfigurer getAuthorizeRequestConfigurer() {
        return authorizeRequestConfigurer;
    }
}
