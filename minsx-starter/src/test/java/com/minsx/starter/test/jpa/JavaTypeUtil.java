package com.minsx.starter.test.jpa;

public class JavaTypeUtil {

    public static Class getClass(String clazz) {
        switch (clazz) {
            case "String":
                return String.class;
            case "Boolean":
                return Boolean.class;
            case "Byte":
                return Byte.class;
            case "Character":
                return Character.class;
            case "Short":
                return Short.class;
            case "Integer":
                return Integer.class;
            case "Long":
                return Long.class;
            case "Float":
                return Float.class;
            case "Double":
                return Double.class;
            case "boolean":
                return boolean.class;
            case "byte":
                return byte.class;
            case "char":
                return char.class;
            case "short":
                return short.class;
            case "int":
                return int.class;
            case "long":
                return long.class;
            case "float":
                return float.class;
            case "double":
                return double.class;
            default:
                return Object.class;
        }
    }


}
