package com.minsx.core.common.entity.base.type;

public enum UserState {

    NORMAL(1), UNKNOWN(0), LOCKED(-1), FORBIDDEN(-2);

    Integer value;

    UserState(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static UserState getUserState(Integer value) {
        switch (value) {
            case 1:
                return NORMAL;
            case -1:
                return LOCKED;
            case -2:
                return FORBIDDEN;
            default:
                return UNKNOWN;
        }
    }

}
