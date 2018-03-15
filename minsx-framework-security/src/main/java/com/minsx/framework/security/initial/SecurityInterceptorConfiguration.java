package com.minsx.framework.security.initial;

import com.alibaba.fastjson.JSON;
import com.minsx.framework.security.aop.AuthorizeHandler;
import com.minsx.framework.security.aop.LoginHandler;
import com.minsx.framework.security.aop.LogoutHandler;
import com.minsx.framework.security.configurer.WebSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class SecurityInterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Autowired
    LoginHandler loginHandler;

    @Autowired
    LogoutHandler logoutHandler;

    @Autowired
    AuthorizeHandler authorizeHandler;

    @Autowired
    WebSecurity webSecurity;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] systemExcludePath = {webSecurity.loginConfigurer().getLoginAPIUrl(),webSecurity.loginConfigurer().getLoginPageUrl()};
        registry.addInterceptor(loginHandler).addPathPatterns(webSecurity.loginConfigurer().getLoginAPIUrl());
        registry.addInterceptor(logoutHandler).addPathPatterns(webSecurity.logoutConfigurer().getLogoutUrl());
        registry.addInterceptor(authorizeHandler).addPathPatterns(webSecurity.authorizeConfigurer().getNeedAuthorize()).excludePathPatterns(systemExcludePath).excludePathPatterns(webSecurity.authorizeConfigurer().getUnNeedAuthorize());
        super.addInterceptors(registry);
    }


}
