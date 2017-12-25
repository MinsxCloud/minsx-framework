package com.minsx.core.common.entity.base.type;

/**
 * 权限类型
 * Created by Joker on 2017/8/31.
 */
public enum GroupType {

    SYSTEM("SYSTEM"), DEVELOPER("DEVELOPER"),UNKNOWN("UNKNOWN");

    String value;

    GroupType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static GroupType getMenuType(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

}
