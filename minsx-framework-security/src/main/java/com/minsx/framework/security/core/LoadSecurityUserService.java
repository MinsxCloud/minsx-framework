package com.minsx.framework.security.core;

import com.minsx.framework.security.base.SecurityUser;
import com.minsx.framework.security.exception.AuthorizationException;

import javax.servlet.http.HttpServletRequest;

public interface LoadSecurityUserService {

    SecurityUser loadUser(HttpServletRequest ServletRequest) throws AuthorizationException;

}
