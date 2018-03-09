package com.minsx.framework.security.exception;

public class AuthorizationException extends RuntimeException {

    private Integer status;
    private String message;

    public AuthorizationException(Integer status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
