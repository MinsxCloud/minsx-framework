package com.minsx.framework.security.api.service;

import com.minsx.framework.security.api.basic.SecurityUser;
import com.minsx.framework.security.api.exception.AuthorizationException;

import javax.servlet.http.HttpServletRequest;

public interface LoadSecurityUserService {

    SecurityUser loadUser(HttpServletRequest ServletRequest) throws AuthorizationException;

}
