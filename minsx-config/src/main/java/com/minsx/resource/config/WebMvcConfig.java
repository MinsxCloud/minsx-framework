package com.minsx.resource.config;

import com.minsx.core.system.service.api.URLAuthInterceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    URLAuthInterceptorService urlAuthInterceptorService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 拦截规则 excludePathPatterns 排除拦截
        registry.addInterceptor(urlAuthInterceptorService).addPathPatterns("/**");
        super.addInterceptors(registry);
    }

}
