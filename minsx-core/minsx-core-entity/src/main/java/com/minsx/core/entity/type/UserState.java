package com.minsx.core.entity.type;

public enum UserState {
	
	NORMAL(1),UNKNOWN(0),LOCKED(-1),FORBIDDEN(-2);

    Integer value;

    UserState(Integer value){
        this.value=value;
    }
    
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    public static UserState getUserState(Integer value) {  
        try {  
            return valueOf(String.valueOf(value));
        }catch (Exception e) {  
            return UNKNOWN;  
        }  
    }  

}
