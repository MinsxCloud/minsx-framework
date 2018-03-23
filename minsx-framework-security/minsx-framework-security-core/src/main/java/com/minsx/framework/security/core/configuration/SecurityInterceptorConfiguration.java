package com.minsx.framework.security.core.configuration;

import com.minsx.framework.security.api.configure.WebSecurity;
import com.minsx.framework.security.core.aop.AuthorizeInterceptor;
import com.minsx.framework.security.core.aop.LoginInterceptor;
import com.minsx.framework.security.core.aop.LogoutInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class SecurityInterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    WebSecurity webSecurity;

    @Autowired
    LoginInterceptor loginHandler;

    @Autowired
    LogoutInterceptor logoutHandler;

    @Autowired
    AuthorizeInterceptor authorizeHandler;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] systemExcludePath = {webSecurity.loginConfigurer().getLoginAPIUrl(), webSecurity.loginConfigurer().getLoginPageUrl()};
        registry.addInterceptor(loginHandler).addPathPatterns(webSecurity.loginConfigurer().getLoginAPIUrl());
        registry.addInterceptor(logoutHandler).addPathPatterns(webSecurity.logoutConfigurer().getLogoutAPIUrl());
        registry.addInterceptor(authorizeHandler).addPathPatterns(webSecurity.authorizeConfigurer().getNeedAuthorize()).excludePathPatterns(systemExcludePath).excludePathPatterns(webSecurity.authorizeConfigurer().getUnNeedAuthorize());
        super.addInterceptors(registry);
    }


}
