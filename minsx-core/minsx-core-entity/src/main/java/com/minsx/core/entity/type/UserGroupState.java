package com.minsx.core.entity.type;

public enum UserGroupState {

	ENABLE(1),DISABLE(-1),UNKNOWN(0);

    Integer value;

    UserGroupState(Integer value){
        this.value=value;
    }
    
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    public static UserGroupState getUserGroupState(Integer value) {  
        try {  
            return valueOf(String.valueOf(value));
        }catch (Exception e) {  
            return UNKNOWN;  
        }  
    }  
}
