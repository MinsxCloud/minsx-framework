package com.minsx.core.common.entity.base.type;
/**
 * AuthStatus
 * created by Joker on 2017年11月1日
 */
public enum AuthState {
	
	ENABLE(1),DISABLE(-1),UNKNOWN(0);

    Integer value;

    AuthState(Integer value){
        this.value=value;
    }
    
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    public static AuthState getAuthState(Integer value) {
         switch (value) {
            case 1:
                return ENABLE;
            case -1:
                return DISABLE;
            default:
                return UNKNOWN;
        }
    }

}
