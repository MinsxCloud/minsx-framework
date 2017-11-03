package com.minsx.core.entity.type;

public enum MenuState {

	ENABLE(1),DISABLE(-1),UNKNOWN(0);

    Integer value;

    MenuState(Integer value){
        this.value=value;
    }
    
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
    
    public static MenuState getMenuState(Integer value) {  
        try {  
            return valueOf(String.valueOf(value));
        }catch (Exception e) {  
            return UNKNOWN;  
        }  
    }  
}
