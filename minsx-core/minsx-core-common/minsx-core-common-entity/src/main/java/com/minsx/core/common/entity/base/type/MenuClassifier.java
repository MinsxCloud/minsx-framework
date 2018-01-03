package com.minsx.core.common.entity.base.type;

/**
 * 菜单分类
 * Created by Joker on 2017/8/31.
 */
public enum MenuClassifier {

    TOP("TOP"), LEFT("LEFT"), UNKNOWN("UNKNOWN");

    String value;

    MenuClassifier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static MenuClassifier getMenuClassifier(String value) {
        try {
            return valueOf(value);
        } catch (Exception e) {
            return UNKNOWN;
        }
    }

}
