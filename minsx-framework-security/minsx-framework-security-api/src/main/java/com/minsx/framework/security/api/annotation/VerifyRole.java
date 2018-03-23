package com.minsx.framework.security.api.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface VerifyRole {

    /**
     * 角色值
     */
    String[] value() default {};

    /**
     * 角色之间的逻辑
     */
    Logic logic() default Logic.DEFAULT;

    /**
     * 角色验证顺序：在执行方法前验证还是在执行方法后验证
     */
    Order order() default Order.DEFAULT;

    /**
     * 角色范围：是否包涵以上权限才能执行方法或是除以上权限外都能执行方法
     */
    Scope scope() default Scope.DEFAULT;

    /**
     * 验证不通过后返回的状态码
     */
    int status() default 403;

     /**
     * 验证不通过后返回的消息
     */
    String message() default "unauthorized";

    /**
     * 是否对该注解进行验证
     */
    boolean enabled() default true;

}
