package com.minsx.framework.security.exception;

public class AccessDenyException extends AuthorizationException{

    public AccessDenyException(Integer status, String message) {
        super(status, message);
    }
}
