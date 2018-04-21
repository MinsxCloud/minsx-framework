package com.minsx.framework.security.api.configure;

public interface WebSecurity {

    WebSecurity enabled(Boolean enabled);

    Boolean getEnabled();

    WebSecurity ExpiredTime(Long millSeconds);

    Long getExpiredTime();

    LoginConfigurer loginConfigurer();

    LogoutConfigurer logoutConfigurer();

    AuthorizeConfigurer authorizeConfigurer();


}
