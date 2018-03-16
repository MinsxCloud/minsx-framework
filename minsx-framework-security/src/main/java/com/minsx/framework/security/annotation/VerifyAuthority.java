/*
 *
 *  * Copyright 2017 https://www.minsx.com
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */
package com.minsx.framework.security.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface VerifyAuthority {

    /**
     * 权限值：格式为：{type:value,value...} 例如：{"user:select,update","admin:delete"}
     */
    String[] value() default {};

    /**
     * 权限之间的逻辑
     */
    Logic logic() default Logic.DEFAULT;

    /**
     * 权限验证顺序：在执行方法前验证还是在执行方法后验证
     */
    Order order() default Order.DEFAULT;

    /**
     * 权限范围：是否包涵以上权限才能执行方法或是除以上权限外都能执行方法
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
