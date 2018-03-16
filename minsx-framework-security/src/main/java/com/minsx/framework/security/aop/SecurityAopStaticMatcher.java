package com.minsx.framework.security.aop;

import com.minsx.framework.security.annotation.VerifyAuthority;
import com.minsx.framework.security.annotation.VerifyExpression;
import com.minsx.framework.security.annotation.VerifyRole;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.lang.Nullable;

import java.lang.reflect.Method;

public class SecurityAopStaticMatcher extends StaticMethodMatcherPointcutAdvisor {

    public SecurityAopStaticMatcher(SecurityAopAdvise securityAopAdvise) {
        setAdvice(securityAopAdvise);
    }

    @Override
    public boolean matches(Method method, @Nullable Class<?> aClass) {
        VerifyAuthority verifyAuthority = method.getDeclaredAnnotation(VerifyAuthority.class);
        VerifyRole verifyRole = method.getDeclaredAnnotation(VerifyRole.class);
        VerifyExpression verifyExpression = method.getDeclaredAnnotation(VerifyExpression.class);
        return verifyAuthority != null || verifyRole != null || verifyExpression != null;
    }

}

