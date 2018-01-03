package com.minsx.core.common.entity.base.type;

/**
 * 分组类型
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

    public static GroupType getGroupType(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

}
