
package com.minsx.framework.security.aop;

import com.minsx.framework.security.annotation.Authority;
import com.minsx.framework.security.exception.AuthorizationException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SecurityAopAdvise implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Authority authority = methodInvocation.getMethod().getDeclaredAnnotation(Authority.class);
        if (authority != null) {
            throw new AuthorizationException(authority.status(), authority.message());
        }
        return methodInvocation.proceed();
    }

}
