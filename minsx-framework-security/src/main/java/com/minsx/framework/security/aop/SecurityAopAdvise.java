
package com.minsx.framework.security.aop;

import com.alibaba.fastjson.JSON;
import com.minsx.framework.security.annotation.Authority;
import com.minsx.framework.security.annotation.Logic;
import com.minsx.framework.security.core.Authentication;
import com.minsx.framework.security.core.CustomAuthorize;
import com.minsx.framework.security.exception.AuthorizationException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecurityAopAdvise implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Authority authority = methodInvocation.getMethod().getDeclaredAnnotation(Authority.class);
        if (authority != null) {
            String[] authorities = authority.value();
            Boolean hasAuthority = true;
            Authentication authentication = Authentication.current();
            if (authentication != null) {
                List<CustomAuthorize> customAuthorizes = authentication.getSecurityUser().getCustomAuthorizes();
                if (customAuthorizes != null) {
                    for (String auth : authorities) {
                        if (auth != null && auth.contains(":")) {
                            String type = auth.substring(0, auth.indexOf(":"));
                            String preValues = auth.substring(auth.indexOf(":") + 1, auth.length());
                            List<String> values = new ArrayList<>();
                            if (preValues.contains(",")) {
                                values = Arrays.asList(preValues.split(","));
                            } else {
                                values.add(preValues);
                            }
                            Boolean matched = true;
                            for (String v : values) {
                                Boolean m = customAuthorizes.stream().anyMatch(c -> c.getType().equalsIgnoreCase(type) && c.getValue().equalsIgnoreCase(v));
                                if (!m) {
                                    matched = false;
                                    break;
                                }
                            }
                            if (!matched && (authority.logic().equals(Logic.AND) || authority.logic().equals(Logic.DEFAULT))) {
                                hasAuthority = false;
                                break;
                            }

                            if (matched && authority.logic().equals(Logic.OR)) {
                                hasAuthority = true;
                                break;
                            }

                            System.out.println(matched ? "具有" : "没有" + "权限=" + auth);
                            System.out.println(type);
                            System.out.println(JSON.toJSONString(values));
                        }
                    }
                }else {
                    hasAuthority = false;
                }
            } else {
                hasAuthority = false;
            }

            if (!hasAuthority) {
                throw new AuthorizationException(authority.status(), authority.message());
            }
        }
        return methodInvocation.proceed();
    }

}
