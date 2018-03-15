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
package com.minsx.framework.security.simple;

import com.minsx.framework.common.basic.ThreadLocalUtil;
import com.minsx.framework.security.core.Authentication;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class AuthenticationHolder {

    private static final String AUTHENTICATION_KEY = Authentication.class.getName();

    private static final ReadWriteLock LOCK = new ReentrantReadWriteLock();

    public static Authentication put(Authentication authentication) {
        return ThreadLocalUtil.put(AUTHENTICATION_KEY, authentication);
    }

    public static Authentication get() {
        return ThreadLocalUtil.get(AUTHENTICATION_KEY);
    }

}
