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
package com.minsx.framework.security.core.configuration;

import com.minsx.framework.security.api.authentication.AuthenticationManager;
import com.minsx.framework.security.api.configure.WebSecurity;
import com.minsx.framework.security.api.simple.SimpleAuthenticationManager;
import com.minsx.framework.security.api.simple.SimpleUserTokenManager;
import com.minsx.framework.security.api.token.UserTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AutoConfigureAfter(SecurityAutoConfiguration.class)
public class AuthorizeTokenConfiguration {

    @Autowired
    WebSecurity webSecurity;

    @Bean
    public AuthenticationManager authenticationManager() {
        return new SimpleAuthenticationManager();
    }

    @Bean
    public UserTokenManager userTokenManager(AuthenticationManager authenticationManager) {
        return new SimpleUserTokenManager(authenticationManager, webSecurity.getExpiredTime());
    }

}
