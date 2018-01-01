package com.minsx.core.common.entity.base.type;

/**
 * 权限类型
 * Created by Joker on 2017/8/31.
 */
public enum CustomSettingType {

    BASIC("BASIC"), UNKNOWN("UNKNOWN");

    String value;

    CustomSettingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static CustomSettingType getCustomSettingType(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

}
