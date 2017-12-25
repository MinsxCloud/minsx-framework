package com.minsx.core.common.entity.base.type;

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
