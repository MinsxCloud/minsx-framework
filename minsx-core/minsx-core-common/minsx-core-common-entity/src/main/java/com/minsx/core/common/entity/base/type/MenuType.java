package com.minsx.core.common.entity.base.type;

/**
 * 菜单值类型
 * Created by Joker on 2017/8/31.
 */
public enum MenuType {

    MENU("MENU"), LINK("LINK"), IFRAME("IFRAME"), COMMAND("COMMAND"), NONE("NONE"), UNKNOWN("UNKNOWN");

    String value;

    MenuType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static MenuType getMenuType(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

}
