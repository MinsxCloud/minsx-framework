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
import com.minsx.framework.security.api.token.UserTokenManager;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleUserTokenManager implements UserTokenManager {

    private final ConcurrentMap<String, SimpleUserToken> TOKEN_POOL;

    private final ConcurrentMap<String, List<String>> USER_POOL;

    private final static Long MAX_INACTIVE_INTERVAL = 24 * 60 * 60 * 1000L;

    public SimpleUserTokenManager() {
        this(new ConcurrentHashMap<>(128), new ConcurrentHashMap<>(128));
    }

    public SimpleUserTokenManager(ConcurrentMap<String, SimpleUserToken> TOKEN_POOL, ConcurrentMap<String, List<String>> USER_POOL) {
        this.TOKEN_POOL = TOKEN_POOL;
        this.USER_POOL = USER_POOL;
    }

    @Override
    public UserToken getUserTokenByToken(String token) {
        SimpleUserToken simpleUserToken = TOKEN_POOL.get(token);
        if (simpleUserToken != null) {
            if (simpleUserToken.getInactiveTime() > MAX_INACTIVE_INTERVAL) {
                simpleUserToken.setState(TokenState.EXPIRED);
            }
        }
        return simpleUserToken;
    }

    @Override
    public List<UserToken> getUserTokensByUsername(String username) {
        List<String> tokens = USER_POOL.get(username);
        if (tokens != null) {
            return tokens.stream().map(this::getUserTokenByToken).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public List<UserToken> getAllActiveUserTokens() {
        return TOKEN_POOL.values().stream().filter(u ->
                u.getInactiveTime() < MAX_INACTIVE_INTERVAL
        ).collect(Collectors.toList());
    }

    @Override
    public List<UserToken> getAllExpiredUserTokens() {
        return TOKEN_POOL.values().stream().filter(u ->
                u.getInactiveTime() > MAX_INACTIVE_INTERVAL
        ).peek(u -> u.setState(TokenState.EXPIRED)).collect(Collectors.toList());
    }

    @Override
    public List<UserToken> getAllForbiddenUserTokens() {
        return TOKEN_POOL.values().stream().filter(u ->
                u.getState().equals(TokenState.FORBIDDEN)
        ).collect(Collectors.toList());
    }

    @Override
    public List<UserToken> getAllToken() {
        updateAllUserTokensState();
        return new ArrayList<>(TOKEN_POOL.values());
    }

    @Override
    public Long totalTokensCount() {
        return (long) TOKEN_POOL.size();
    }

    @Override
    public Long totalUsersCount() {
        return (long) USER_POOL.size();
    }

    @Override
    public void clearUserTokenByToken(String token) {
        UserToken userToken = TOKEN_POOL.remove(token);
        if (userToken != null) {
            String username = userToken.getUsername();
            List<String> tokens = USER_POOL.get(username);
            if (tokens != null) {
                tokens.remove(token);
            } else {
                USER_POOL.remove(username);
            }
        }
    }

    @Override
    public void clearUserTokenByUsername(String username) {
        List<String> tokens = USER_POOL.get(username);
        if (tokens != null) {
            tokens.forEach(TOKEN_POOL::remove);
        }
    }

    @Override
    public void updateUserTokenStateByToken(String token, TokenState tokenState) {
        if (token == null || tokenState == null) {
            return;
        }
        SimpleUserToken userToken = TOKEN_POOL.get(token);
        if (userToken != null) {
            userToken.setState(tokenState);
        }
    }

    @Override
    public void updateUserTokensStateByUsername(String username, TokenState tokenState) {
        List<String> tokens = USER_POOL.get(username);
        if (tokens != null) {
            tokens.forEach(t -> updateUserTokenStateByToken(t, tokenState));
        }
    }

    @Override
    public void updateAllUserTokensState() {
        TOKEN_POOL.values().forEach(t -> {
            if (t.getInactiveTime() > MAX_INACTIVE_INTERVAL) {
                t.setState(TokenState.EXPIRED);
            }
        });
    }

    @Override
    public void clearExpiredUserTokens() {
        TOKEN_POOL.values().forEach(t -> {
            if (t.getInactiveTime() > MAX_INACTIVE_INTERVAL) {
                TOKEN_POOL.remove(t.getToken());
            }
        });
    }

    @Override
    public void clearForbiddenUserTokens() {
        TOKEN_POOL.values().forEach(t -> {
            if (t.getState().equals(TokenState.FORBIDDEN)) {
                TOKEN_POOL.remove(t.getToken());
            }
        });
    }

    @Override
    public void flushUserToken(String token) {
        SimpleUserToken userToken = TOKEN_POOL.get(token);
        if (userToken != null) {
            userToken.flush();
        }
    }

    @Override
    public Boolean isExistUserToken(String token) {
        return TOKEN_POOL.get(token) != null;
    }

    @Override
    public Boolean isExistUser(String username) {
        return USER_POOL.get(username) != null;
    }

    @Override
    public UserToken login(String token, String username) {
        SimpleUserToken userToken = new SimpleUserToken();
        userToken.setUsername(username);
        userToken.setToken(token);
        userToken.setState(TokenState.ACTIVE);
        TOKEN_POOL.put(token, userToken);

        List<String> tokens = USER_POOL.computeIfAbsent(username, key -> new ArrayList<>());
        if (tokens != null) {
            tokens.add(token);
        } else {
            USER_POOL.put(username, Arrays.asList(token));
        }
        return userToken;
    }

    @Override
    public UserToken logoutByToken(String token) {
        UserToken userToken = TOKEN_POOL.remove(token);
        if (userToken != null) {
            List<String> tokens = USER_POOL.get(userToken.getUsername());
            if (tokens != null) {
                tokens.remove(token);
            }
        }
        return userToken;
    }

    @Override
    public List<UserToken> signOutByUsername(String username) {
        List<UserToken> userTokens = new ArrayList<>();
        List<String> tokens = USER_POOL.remove(username);
        if (tokens != null) {
            tokens.forEach(t -> {
                UserToken userToken = TOKEN_POOL.remove(t);
                if (userToken != null) {
                    userTokens.add(userToken);
                }
            });
        }
        return userTokens;
    }

    @Override
    public Stream<SimpleUserToken> handleActiveUserToken(Consumer<SimpleUserToken> consumer) {
        return TOKEN_POOL.values().stream().filter(t -> t.getInactiveTime() < MAX_INACTIVE_INTERVAL).peek(consumer);
    }

}