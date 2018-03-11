package com.minsx.framework.common.exceltest;

public interface BeanMapper<T> {

    T mapper(Row row);

}
