package com.minsx.framework.security.aop;

import com.minsx.framework.security.annotation.Authority;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class SecurityAopMatcher extends StaticMethodMatcherPointcutAdvisor {

    public SecurityAopMatcher(SecurityAopAdvise securityAopAdvise) {
        setAdvice(securityAopAdvise);
    }

    @Override
    public boolean matches(Method method, @Nullable Class<?> aClass) {
        Authority requireAuthority = method.getDeclaredAnnotation(Authority.class);
        return requireAuthority != null;
    }

}

