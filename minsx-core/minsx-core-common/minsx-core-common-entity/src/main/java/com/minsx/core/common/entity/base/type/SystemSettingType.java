package com.minsx.core.common.entity.base.type;

/**
 * 系统设置类型
 * Created by Joker on 2017/8/31.
 */
public enum SystemSettingType {

    SITE_INFO("SITE_INFO"),REGISTER("REGISTER"),BASIC("BASIC"), UNKNOWN("UNKNOWN");

    String value;

    SystemSettingType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static SystemSettingType getSystemSettingType(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

}
