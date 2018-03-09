package com.minsx.framework.security.base;


public enum AuthorizeType {

    URL("URL"), METHOD("METHOD"), CUSTOM("CUSTOM"), UNKNOWN("UNKNOWN");

    String value;

    AuthorizeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static AuthorizeType getAuthorityType(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

}
