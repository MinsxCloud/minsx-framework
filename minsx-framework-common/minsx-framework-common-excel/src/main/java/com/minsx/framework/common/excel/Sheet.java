package com.minsx.framework.common.excel;

import java.util.List;

public class Sheet {

    private List<Row> rows;

    public Sheet(){}

    public Sheet(List<Row> rows) {
        this.rows = rows;
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setRows(List<Row> rows) {
        this.rows = rows;
    }
}
