package com.minsx.framework.security.response;

import java.util.Date;

public class ResponseMsg {

    private Integer status;
    private String message;
    private Date timestamp;

    public ResponseMsg() {
        this.timestamp = new Date();
    }

    public ResponseMsg(Integer status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
