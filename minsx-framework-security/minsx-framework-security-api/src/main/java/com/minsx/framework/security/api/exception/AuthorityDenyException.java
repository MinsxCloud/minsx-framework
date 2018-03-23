package com.minsx.framework.security.api.exception;

public class AuthorityDenyException extends AuthorizationException{

    public AuthorityDenyException(Integer status, String message) {
        super(status, message);
    }
}
