package com.minsx.framework.security.base;

/**
 * 用户权限
 */
public class Authorize implements Authorised {

    private String type;

    private String value;

    public Authorize(String type, String value) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authorize authority = (Authorize) o;
        return (type != null ? type.equals(authority.type) : authority.type == null) && (value != null ? value.equals(authority.value) : authority.value == null);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
