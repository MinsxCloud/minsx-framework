package com.minsx.framework.common.exceltest;

public class ExcelReadException extends RuntimeException {

    public ExcelReadException(String message) {
        super(message);
    }

    ExcelReadException(Throwable t) {
        super(t);
    }

    public ExcelReadException(String message, Throwable t) {
        super(message, t);
    }

}
