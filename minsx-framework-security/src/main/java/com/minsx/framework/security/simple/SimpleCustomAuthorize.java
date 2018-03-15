package com.minsx.framework.security.simple;

import com.minsx.framework.security.core.CustomAuthorize;

/**
 * 普通权限
 */
public class SimpleCustomAuthorize implements CustomAuthorize {

    private String type;

    private String value;

    public SimpleCustomAuthorize(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
