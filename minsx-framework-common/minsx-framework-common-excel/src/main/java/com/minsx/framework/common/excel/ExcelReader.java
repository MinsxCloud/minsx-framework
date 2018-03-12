package com.minsx.framework.common.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ExcelReader {

    private List<Sheet> sheets;

    public ExcelReader(InputStream inputStream) {
        sheets = ExcelOperator.readExcel(inputStream);
    }

    public ExcelReader(String excelPath) throws FileNotFoundException {
        sheets = ExcelOperator.readExcel(new FileInputStream(excelPath));
    }

    public int getSheetNum() {
        return sheets.size();
    }

    public <T> List<T> getBeansOfSheet(int sheetIndex, BeanMapper<T> beanMapper) {
        return ExcelOperator.getBeansOfSheet(sheets.get(sheetIndex), beanMapper);
    }

    public List<Sheet> getAllSheet() {
        return sheets;
    }

    public List<Sheet> getAllSheet(DataMapper dataMapper) {
        return ExcelOperator.getAllSheetData(sheets, dataMapper);
    }

    public Sheet getSheet(int sheetIndex) {
        return sheets.get(sheetIndex);
    }

    public Sheet getSheet(int sheetIndex, DataMapper dataMapper) {
        return ExcelOperator.getSheetData(sheets.get(sheetIndex), dataMapper);
    }

    public Row getRow(int sheetIndex, int RowIndex) {
        return sheets.get(sheetIndex).getRows().get(RowIndex);
    }

    public Cell getCell(int sheetIndex, int RowIndex, int cellIndex) {
        return sheets.get(sheetIndex).getRows().get(RowIndex).getCells().get(cellIndex);
    }


}
