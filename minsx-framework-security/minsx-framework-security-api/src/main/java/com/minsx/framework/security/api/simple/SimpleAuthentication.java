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
package com.minsx.framework.security.api.simple;

import com.minsx.framework.security.api.authentication.Authentication;
import com.minsx.framework.security.api.basic.SecurityUser;

public class SimpleAuthentication implements Authentication {

    private Boolean authenticated;

    private SecurityUser securityUser;

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        this.authenticated = isAuthenticated;
    }

    @Override
    public SecurityUser getSecurityUser() {
        return securityUser;
    }

    @Override
    public void setSecurityUser(SecurityUser securityUser) {
        this.securityUser = securityUser;
    }
}
