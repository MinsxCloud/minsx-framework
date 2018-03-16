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
public @interface VerifyExpression {

    /**
     * 表达式值,脚本执行后需返回Boolean值
     */
    String[] value();

    /**
     * 表达式语言支持：Spel/JavaScript/Ognl,Groovy
     */
    Script[] script() default {Script.SPEL};

    /**
     * 表达式之间的逻辑
     */
    Logic logic() default Logic.DEFAULT;

    /**
     * 表达式验证顺序,在执行方法前验证还是在执行方法后验证
     */
    Order order() default Order.DEFAULT;

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
