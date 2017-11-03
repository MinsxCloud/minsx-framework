package com.minsx.core.entity.type;
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
        try {  
            return valueOf(String.valueOf(value));
        }catch (Exception e) {  
            return UNKNOWN;  
        }  
    }  

}
