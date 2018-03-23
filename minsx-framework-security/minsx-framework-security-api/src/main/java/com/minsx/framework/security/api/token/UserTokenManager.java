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
package com.minsx.framework.security.api.token;

import com.minsx.framework.security.api.simple.SimpleUserToken;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface UserTokenManager {

    UserToken getUserTokenByToken(String token);

    List<UserToken> getUserTokensByUsername(String username);

    List<UserToken> getAllActiveUserTokens();

    List<UserToken> getAllExpiredUserTokens();

    List<UserToken> getAllForbiddenUserTokens();

    List<UserToken> getAllToken();

    Long totalTokensCount();

    Long totalUsersCount();

    void clearUserTokenByToken(String token);

    void clearUserTokenByUsername(String username);

    void updateUserTokenStateByToken(String token, TokenState tokenState);

    void updateUserTokensStateByUsername(String username, TokenState tokenState);

    void updateAllUserTokensState();

    void clearExpiredUserTokens();

    void clearForbiddenUserTokens();

    void flushUserToken(String token);

    Boolean isExistUserToken(String token);

    Boolean isExistUser(String username);

    UserToken login(String token, String username);

    UserToken logoutByToken(String token);

    List<UserToken> signOutByUsername(String username);

    Stream<SimpleUserToken> handleActiveUserToken(Consumer<SimpleUserToken> consumer);



}
