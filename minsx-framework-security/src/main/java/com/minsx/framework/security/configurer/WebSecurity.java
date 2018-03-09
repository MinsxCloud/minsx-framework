package com.minsx.framework.security.configurer;

public interface WebSecurity {

    WebSecurity enabled(Boolean enabled);

    Boolean getEnabled();

    LoginConfigurer loginConfigurer();

    LogoutConfigurer logoutConfigurer();

    AuthorizeRequestConfigurer authorizeConfigurer();

}
