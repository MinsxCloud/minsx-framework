package com.minsx.framework.common.excel;

import com.alibaba.fastjson.annotation.JSONField;

public class Cell {

    private String columnName;

    private String value;

    private String MRValue;

    private String type;

    public Cell() {
    }

    public Cell(Cell cell) {
        this.columnName = cell.getColumnName();
        this.value = cell.getValue();
        this.MRValue = cell.getMRValue();
        this.type = cell.getType();
    }

    public Cell(String columnName, String value, String MRValue, String type) {
        this.columnName = columnName;
        this.value = value;
        this.MRValue = MRValue;
        this.type = type;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMRValue() {
        return MRValue;
    }

    public void setMRValue(String MRValue) {
        this.MRValue = MRValue;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JSONField(serialize=false)
    public String getValueOrMRValue() {
        return this.value == null ? this.MRValue : this.value;
    }

}
