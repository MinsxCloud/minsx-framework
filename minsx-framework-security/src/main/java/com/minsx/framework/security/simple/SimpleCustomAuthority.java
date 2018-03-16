package com.minsx.framework.security.simple;

import com.minsx.framework.security.core.CustomAuthority;

/**
 * 普通权限
 */
public class SimpleCustomAuthority implements CustomAuthority {

    private String type;

    private String value;

    public SimpleCustomAuthority(String type, String value) {
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
