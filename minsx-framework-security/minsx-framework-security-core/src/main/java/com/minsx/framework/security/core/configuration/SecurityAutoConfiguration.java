package com.minsx.framework.security.core.configuration;

import com.minsx.framework.security.api.authentication.AuthenticationManager;
import com.minsx.framework.security.api.configure.WebSecurity;
import com.minsx.framework.security.api.handler.LoginHandler;
import com.minsx.framework.security.api.simple.SimpleAuthenticationManager;
import com.minsx.framework.security.api.simple.SimpleUserTokenManager;
import com.minsx.framework.security.api.simple.WebSecurityBuilder;
import com.minsx.framework.security.api.token.UserTokenManager;
import com.minsx.framework.security.core.aop.*;
import com.minsx.framework.security.core.handler.DefaultLoginHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.minsx.framework.security")
public class SecurityAutoConfiguration {

    @Bean
    public WebSecurity webSecurity(){
        return new WebSecurityBuilder();
    }

    @Bean
    public AuthenticationManager authenticationManager(){
        return new SimpleAuthenticationManager();
    }

    @Bean
    public SecurityAopAdvise securityAopAdvise(){
        return new SecurityAopAdvise();
    }

    @Bean
    public SecurityAopStaticMatcher securityAopMatcher(SecurityAopAdvise securityAopAdvise) {
        return new SecurityAopStaticMatcher(securityAopAdvise);
    }

    @Bean
    public AuthorizeInterceptor authorizeInterceptor() {
        return new AuthorizeInterceptor();
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public LogoutInterceptor logoutInterceptor() {
        return new LogoutInterceptor();
    }

    @Bean
    public UserTokenManager userTokenManager(){
        return new SimpleUserTokenManager();
    }

    @Bean
    public LoginHandler loginHandler(){
        return new DefaultLoginHandler();
    }


}
