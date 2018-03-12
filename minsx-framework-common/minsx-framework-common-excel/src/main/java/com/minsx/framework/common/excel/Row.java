package com.minsx.framework.common.excel;

import java.util.List;

public class Row {

    private List<Cell> cells;

    public Row(){}

    public Row(List<Cell> cells) {
        this.cells = cells;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
