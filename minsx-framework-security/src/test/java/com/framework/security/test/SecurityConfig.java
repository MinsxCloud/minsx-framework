package com.framework.security.test;

import com.minsx.framework.security.configurer.WebSecurity;
import com.minsx.framework.security.configurer.WebSecurityConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig implements WebSecurityConfigurer {

    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity
                .enabled(true)
                .authorizeConfigurer()
                .needAuthorize("/**")
                .unNeedAuthorize("/login", "/logout", "/api/login", "/")
                .and()
                .loginConfigurer()
                .loginAPIUrl("/api/login")
                .loginPageUrl("/login")
                .loginFailureUrl("/login")
                .loginSuccessUrl("/user")
                .and()
                .logoutConfigurer()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

    }


}
