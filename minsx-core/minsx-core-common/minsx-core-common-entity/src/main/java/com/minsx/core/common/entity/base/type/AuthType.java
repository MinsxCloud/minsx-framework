package com.minsx.core.common.entity.base.type;

/**
 * 权限类型
 * Created by Joker on 2017/8/31.
 */
public enum AuthType {

    URL("URL"),MENU("MENU"),DATA("DATA"),BUTTON("BUTTON"),METHOD("METHOD"),UNKNOWN("UNKNOWN");

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
    
    public static AuthType getAuthType(String value) {
        try {
            return valueOf(value);
        }catch (Exception e) {  
            return UNKNOWN;  
        }  
    }  

}
