package com.minsx.service.entity;

/**
 * AuthType
 * Created by Joker on 2017/8/31.
 */
public enum AuthType {

    URL("URL"),MENU("MENU"),DATA("DATA");

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
