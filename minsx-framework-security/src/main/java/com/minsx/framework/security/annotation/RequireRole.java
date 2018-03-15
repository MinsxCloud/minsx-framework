package com.minsx.framework.security.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RequireRole {

    String value();

    int status() default 403;

    String message() default "unauthorized";

    boolean enabled() default true;

}
