package com.minsx.core.entity.type;

/**
 * 权限类型
 * Created by Joker on 2017/8/31.
 */
public enum AuthType {

    URL("URL"),MENU("MENU"),DATA("DATA"),BUTTON("BUTTON");

    String value;

    AuthType(String value){
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }



}
