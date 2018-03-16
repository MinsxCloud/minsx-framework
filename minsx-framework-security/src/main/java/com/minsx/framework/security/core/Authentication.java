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
package com.minsx.framework.security.core;

import com.minsx.framework.security.simple.AuthenticationHolder;

import java.io.Serializable;

public interface Authentication extends Serializable {

    static Authentication current() {
        return AuthenticationHolder.get();
    }

    boolean isAuthenticated();

    void setAuthenticated(boolean isAuthenticated);

    SecurityUser getSecurityUser();

    void setSecurityUser(SecurityUser securityUser);

    default boolean hasRole(String name) {
        return getSecurityUser().getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(name));
    }

    default boolean hasCustomAuthorize(String type, String value) {
        return getSecurityUser().getCustomAuthorities().stream()
                .anyMatch(auth -> auth.getType().equalsIgnoreCase(type) && auth.getValue().equalsIgnoreCase(value));
    }

    default boolean hasRequestAuthorize(String url, String method, String params) {
        return getSecurityUser().getRequestAuthorities().stream()
                .anyMatch(auth -> auth.getURI().equalsIgnoreCase(url)
                        && auth.getMethod().equalsIgnoreCase(method) && auth.getParams().equalsIgnoreCase(params));
    }


}
