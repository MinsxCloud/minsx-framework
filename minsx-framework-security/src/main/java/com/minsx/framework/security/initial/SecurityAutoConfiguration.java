package com.minsx.framework.security.initial;

import com.minsx.framework.security.aop.SecurityAopAdvise;
import com.minsx.framework.security.aop.SecurityAopMatcher;
import com.minsx.framework.security.configurer.WebSecurity;
import com.minsx.framework.security.configurer.WebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@AutoConfigureBefore(SecurityInterceptorConfiguration.class)
public class SecurityAutoConfiguration {

    @Autowired
    WebSecurity webSecurity;

    @Autowired
    WebSecurityConfigurer webSecurityConfigurer;

    @Bean
    public SecurityAopMatcher securityAopMatcher(){
        return new SecurityAopMatcher(new SecurityAopAdvise());
    }

    @PostConstruct
    public void initializeConfiguration() {
        webSecurityConfigurer.configure(webSecurity);
    }


}
