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

import com.minsx.framework.security.api.token.TokenState;
import com.minsx.framework.security.api.token.UserToken;

import java.util.concurrent.atomic.AtomicLong;

public class SimpleUserToken implements UserToken {

    private String username;
    private String token;
    private Long requestTimes;
    private AtomicLong requestTimesCounter = new AtomicLong(0);
    private Long lastRequestTime = System.currentTimeMillis();
    private Long signInTime = System.currentTimeMillis();
    private TokenState state;

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getToken() {
        return this.token;
    }

    @Override
    public Long getRequestTimes() {
        return this.requestTimesCounter.get();
    }

    @Override
    public Long getLastRequestTime() {
        return this.lastRequestTime;
    }

    @Override
    public Long getLoginTime() {
        return this.signInTime;
    }

    @Override
    public TokenState getState() {
        return this.state;
    }

    @Override
    public Long getInactiveTime() {
        return System.currentTimeMillis() - this.lastRequestTime;
    }

    @Override
    public Long getActiveTime() {
        return this.lastRequestTime - this.signInTime;
    }

    public void flush() {
        if (!this.state.equals(TokenState.FORBIDDEN)) {
            this.state = TokenState.ACTIVE;
        }
        this.requestTimesCounter.addAndGet(1);
        this.lastRequestTime = System.currentTimeMillis();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRequestTimes(Long requestTimes) {
        this.requestTimes = requestTimes;
        requestTimesCounter.set(requestTimes);
    }

    public AtomicLong getRequestTimesCounter() {
        return requestTimesCounter;
    }

    public void setRequestTimesCounter(AtomicLong requestTimesCounter) {
        this.requestTimesCounter = requestTimesCounter;
    }

    public void setLastRequestTime(Long lastRequestTime) {
        this.lastRequestTime = lastRequestTime;
    }

    public void setSignInTime(Long signInTime) {
        this.signInTime = signInTime;
    }

    public void setState(TokenState state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleUserToken that = (SimpleUserToken) o;
        return token.equals(that.token);
    }

    @Override
    public int hashCode() {
        return token.hashCode();
    }
}
