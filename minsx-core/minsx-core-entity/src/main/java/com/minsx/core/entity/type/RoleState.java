package com.minsx.core.entity.type;

public enum RoleState {

	ENABLE(1),DISABLE(-1),UNKNOWN(0);

    Integer value;

    RoleState(Integer value){
        this.value=value;
    }
    
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    public static RoleState getRoleState(Integer value) {  
        try {  
            return valueOf(String.valueOf(value));
        }catch (Exception e) {  
            return UNKNOWN;  
        }  
    }  
}
