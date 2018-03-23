package com.minsx.framework.security.api.configure;

public interface WebSecurity {

    WebSecurity enabled(Boolean enabled);

    Boolean getEnabled();

    LoginConfigurer loginConfigurer();

    LogoutConfigurer logoutConfigurer();

    AuthorizeConfigurer authorizeConfigurer();

}
