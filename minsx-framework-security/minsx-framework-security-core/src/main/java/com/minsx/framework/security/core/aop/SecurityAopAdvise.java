
package com.minsx.framework.security.core.aop;

import com.alibaba.fastjson.JSON;
import com.minsx.framework.security.api.annotation.*;
import com.minsx.framework.security.api.authentication.Authentication;
import com.minsx.framework.security.api.authority.CustomAuthority;
import com.minsx.framework.security.api.basic.Role;
import com.minsx.framework.security.api.exception.AuthorizationException;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecurityAopAdvise implements MethodInterceptor {

    private static final ExpressionParser SPEL_PARSER = new SpelExpressionParser();

    private static final LocalVariableTableParameterNameDiscoverer PND = new LocalVariableTableParameterNameDiscoverer();

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Object result = null;
        VerifyAuthority verifyAuthority = methodInvocation.getMethod().getDeclaredAnnotation(VerifyAuthority.class);
        if (verifyAuthority != null) {
            if (verifyAuthority.order().equals(Order.AFTER)) {
                result = methodInvocation.proceed();
            }
            if (!handleAuthority(verifyAuthority)) {
                throw new AuthorizationException(verifyAuthority.status(), verifyAuthority.message());
            }
            if (verifyAuthority.order().equals(Order.BEFORE) || verifyAuthority.order().equals(Order.DEFAULT)) {
                result = methodInvocation.proceed();
            }
        }

        VerifyRole verifyRole = methodInvocation.getMethod().getDeclaredAnnotation(VerifyRole.class);
        if (verifyRole != null) {
            if (verifyRole.order().equals(Order.AFTER)) {
                result = methodInvocation.proceed();
            }
            if (!handleRole(verifyRole)) {
                throw new AuthorizationException(verifyRole.status(), verifyRole.message());
            }
            if (verifyRole.order().equals(Order.BEFORE) || verifyRole.order().equals(Order.DEFAULT)) {
                result = methodInvocation.proceed();
            }
        }

        VerifyExpression verifyExpression = methodInvocation.getMethod().getDeclaredAnnotation(VerifyExpression.class);
        if (verifyExpression != null) {
            if (verifyExpression.order().equals(Order.AFTER)) {
                result = methodInvocation.proceed();
            }
            if (!handleExpression(methodInvocation.getMethod(), methodInvocation.getArguments(), verifyExpression)) {
                throw new AuthorizationException(verifyExpression.status(), verifyExpression.message());
            }
            if (verifyExpression.order().equals(Order.BEFORE) || verifyExpression.order().equals(Order.DEFAULT)) {
                result = methodInvocation.proceed();
            }
        }

        return result;
    }

    private Boolean handleAuthority(VerifyAuthority authority) {
        String[] authorities = authority.value();
        Boolean hasAuthority = true;
        if (authorities.length != 0) {
            Authentication authentication = Authentication.current();
            if (authentication != null) {
                List<CustomAuthority> customAuthorizes = authentication.getSecurityUser().getCustomAuthorities();
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
                } else {
                    hasAuthority = false;
                }
            } else {
                hasAuthority = false;
            }
        } else {
            hasAuthority = false;
        }
        return hasAuthority;
    }

    private Boolean handleRole(VerifyRole verifyRole) {
        Boolean hasAuthority = true;
        String[] verifyRoles = verifyRole.value();
        if (verifyRoles.length > 0) {
            Authentication authentication = Authentication.current();
            if (authentication != null) {
                List<Role> roles = authentication.getSecurityUser().getRoles();
                if (roles != null && roles.size() > 0) {
                    if (verifyRole.logic().equals(Logic.DEFAULT) || verifyRole.logic().equals(Logic.AND)) {
                        for (String role : verifyRoles) {
                            if (roles.stream().noneMatch(r -> r.getName().equalsIgnoreCase(role))) {
                                hasAuthority = false;
                                break;
                            }
                        }
                    } else {
                        hasAuthority = false;
                        for (String role : verifyRoles) {
                            if (roles.stream().anyMatch(r -> r.getName().equalsIgnoreCase(role))) {
                                hasAuthority = true;
                                break;
                            }
                        }
                    }
                } else {
                    hasAuthority = false;
                }
            } else {
                hasAuthority = false;
            }
        }
        return hasAuthority;
    }

    private Boolean handleExpression(Method method, Object[] args, VerifyExpression verifyExpression) {
        Boolean hasAuthority = true;
        String[] veValues = verifyExpression.value();
        Script[] veScripts = verifyExpression.script();
        if (veValues.length > 0) {
            for (int i = 0; i < veScripts.length; i++) {
                String value = veValues[i];
                Boolean currentResult = false;
                Script script = veScripts.length == veValues.length ? veScripts[i] : veScripts[0];
                switch (script) {
                    case SPEL:
                        EvaluationContext context = new StandardEvaluationContext();
                        String[] parameters = PND.getParameterNames(method);
                        if (parameters != null) {
                            for (int j = 0; j < parameters.length; j++) {
                                context.setVariable(parameters[j], args[j]);
                            }
                        }
                        currentResult = SPEL_PARSER.parseExpression(value).getValue(context, Boolean.class);
                        break;
                    case OGNL:
                        currentResult = true;
                        break;
                    case JAVASCRIPT:
                        currentResult = true;
                        break;
                    case GROOVY:
                        currentResult = true;
                        break;
                    default:
                        currentResult = SPEL_PARSER.parseExpression(value).getValue(Boolean.class);
                        break;
                }
                if (currentResult != null && !currentResult && (verifyExpression.logic().equals(Logic.DEFAULT) || verifyExpression.logic().equals(Logic.AND))) {
                    hasAuthority = false;
                    break;
                }
                if (currentResult != null && currentResult && verifyExpression.logic().equals(Logic.OR)) {
                    hasAuthority = true;
                    break;
                }
            }
        }
        return hasAuthority;
    }
}