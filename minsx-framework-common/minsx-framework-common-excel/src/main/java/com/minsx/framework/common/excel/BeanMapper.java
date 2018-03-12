package com.minsx.framework.common.excel;

public interface BeanMapper<T> {

    T mapper(Row row);

}
