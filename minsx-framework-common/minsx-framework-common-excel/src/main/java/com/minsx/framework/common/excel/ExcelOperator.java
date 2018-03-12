package com.minsx.framework.common.excel;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

class ExcelOperator {

    static <T> List<T> getBeansOfSheet(com.minsx.framework.common.excel.Sheet sheet, BeanMapper<T> beanMapper) {
        List<T> beanList = new ArrayList<>();
        sheet.getRows().forEach(row -> {
            beanList.add(beanMapper.mapper(row));
        });
        return beanList;
    }

    static com.minsx.framework.common.excel.Sheet getSheetData(com.minsx.framework.common.excel.Sheet sheet, DataMapper dataMapper) {
        List<com.minsx.framework.common.excel.Row> newRows = new ArrayList<>();
        sheet.getRows().forEach(row -> {
            List<com.minsx.framework.common.excel.Cell> cells = new ArrayList<>();
            row.getCells().forEach(cell -> {
                cells.add(dataMapper.mapper(cell));
            });
            newRows.add(new com.minsx.framework.common.excel.Row(cells));
        });
        return new com.minsx.framework.common.excel.Sheet(newRows);
    }

    static List<com.minsx.framework.common.excel.Sheet> getAllSheetData(List<com.minsx.framework.common.excel.Sheet> sheets, DataMapper dataMapper) {
        List<com.minsx.framework.common.excel.Sheet> newSheets = new ArrayList<>();
        sheets.forEach(sheet -> {
            List<com.minsx.framework.common.excel.Row> newRows = new ArrayList<>();
            sheet.getRows().forEach(row -> {
                List<com.minsx.framework.common.excel.Cell> newCells = new ArrayList<>();
                row.getCells().forEach(cell -> {
                    newCells.add(dataMapper.mapper(cell));
                });
                newRows.add(new com.minsx.framework.common.excel.Row(newCells));
            });
            newSheets.add(new com.minsx.framework.common.excel.Sheet(newRows));
        });
        return newSheets;
    }

    private static Workbook getWorkbook(InputStream inputStream) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
        } catch (Exception e) {
            throw new ExcelReadException(e);
        }
        return workbook;
    }

    private static List<com.minsx.framework.common.excel.Sheet> handleExcelData(List<com.minsx.framework.common.excel.Sheet> sheets) {
        sheets.forEach(sheet -> {
            for (int i = sheet.getRows().size() - 1; i >= 0; i--) {
                List<com.minsx.framework.common.excel.Cell> cells = sheet.getRows().get(i).getCells();
                boolean isAllNull = true;
                for (com.minsx.framework.common.excel.Cell cell : cells) {
                    if (cell.getValue() != null) {
                        isAllNull = false;
                        break;
                    }
                }
                if (isAllNull) {
                    sheet.getRows().remove(i);
                } else {
                    break;
                }
            }
        });

        List<com.minsx.framework.common.excel.Sheet> newSheets = new ArrayList<com.minsx.framework.common.excel.Sheet>();
        sheets.forEach(sheet -> {
            if (sheet.getRows().size() > 0) {
                int columnNum = sheet.getRows().get(0).getCells().size();
                for (int i = columnNum - 1; i >= 0; i--) {
                    boolean isNullColumn = true;
                    for (com.minsx.framework.common.excel.Row row : sheet.getRows()) {
                        com.minsx.framework.common.excel.Cell cell = row.getCells().get(i);
                        String key = cell.getColumnName();
                        String value = cell.getValue();
                        if (key != null || value != null) {
                            isNullColumn = false;
                            break;
                        }
                    }
                    if (!isNullColumn) {
                        break;
                    } else {
                        final int Index = i;
                        sheet.getRows().forEach(row -> {
                            row.getCells().remove(Index);
                        });
                    }
                }
            }
            newSheets.add(sheet);
        });
        return newSheets;
    }

    static List<com.minsx.framework.common.excel.Sheet> readExcel(InputStream inputStream) {
        List<com.minsx.framework.common.excel.Sheet> dataList = initialExcelData(inputStream);
        return handleExcelData(dataList);
    }

    private static List<com.minsx.framework.common.excel.Sheet> initialExcelData(InputStream inputStream) {
        Workbook workbook = getWorkbook(inputStream);
        List<com.minsx.framework.common.excel.Sheet> sheets = new ArrayList<>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null || sheet.getRow(0) == null) {
                    continue;
                }
                List<String> header = getHeader(sheet);

                List<com.minsx.framework.common.excel.Row> rows = new ArrayList<>();
                for (int rowNum = sheet.getFirstRowNum() + 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
                    List<com.minsx.framework.common.excel.Cell> cells = new ArrayList<>();
                    Row row = sheet.getRow(rowNum);
                    for (int i = 0; i < header.size(); i++) {
                        Map<String, String> result = getCellData(sheet, row, i);
                        com.minsx.framework.common.excel.Cell cell = new com.minsx.framework.common.excel.Cell();
                        cell.setColumnName(header.get(i));
                        cell.setValue(result.get("value"));
                        cell.setMRValue(result.get("MRValue"));
                        cell.setType(result.get("type"));
                        cells.add(cell);
                    }
                    rows.add(new com.minsx.framework.common.excel.Row(cells));
                }
                sheets.add(new com.minsx.framework.common.excel.Sheet(rows));
            }
            try {
                workbook.close();
            } catch (IOException e) {
                throw new ExcelReadException(e);
            }
        }
        return sheets;
    }

    private static List<String> getHeader(Sheet sheet) {
        List<String> data = new ArrayList<>();
        Row row = sheet.getRow(sheet.getFirstRowNum());
        for (int i = row.getFirstCellNum(); i < getBigestCellNum(sheet); i++) {
            data.add(getCellData(sheet, row, i).get("value"));
        }
        return data;
    }

    private static Map<String, String> getCellData(Sheet sheet, Row row, int cellIndex) {
        Map<String, String> result = new LinkedHashMap<>();
        result.put("value", null);
        result.put("MRValue", null);
        if (row == null) {
            return result;
        }
        if (row.getFirstCellNum() <= cellIndex && cellIndex <= row.getLastCellNum()) {
            result.put("value", getCellData(row.getCell(cellIndex)));
            result.put("type", row.getCell(cellIndex) == null ? "NULL" : row.getCell(cellIndex).getCellTypeEnum().toString());
        }
        if (isMergedRegion(sheet, row.getRowNum(), cellIndex)) {
            result.put("value", null);
            Cell cell = getMergedRegionCell(sheet, row.getRowNum(), cellIndex);
            result.put("type", cell == null ? "NULL" : cell.getCellTypeEnum().toString());
            result.put("MRValue", getMergedRegionValue(sheet, row.getRowNum(), cellIndex));
        }
        return result;
    }

    @SuppressWarnings("deprecation")
    private static String getCellData(Cell cell) {
        String value = null;
        if (cell == null) {
            return null;
        }
        switch (cell.getCellType()) {
            case HSSFCell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = cell.getCellFormula();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC:
                HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
                value = dataFormatter.formatCellValue(cell);
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = null;
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = "#ERROR#";
                break;
        }
        return value;
    }

    private static String getMergedRegionValue(Sheet sheet, int rowNum, int columnNum) {
        Cell cell = getMergedRegionCell(sheet, rowNum, columnNum);
        return cell == null ? null : getCellData(cell);
    }

    private static Cell getMergedRegionCell(Sheet sheet, int rowNum, int columnNum) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();
            if (rowNum >= firstRow && rowNum <= lastRow) {
                if (columnNum >= firstColumn && columnNum <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    return fRow.getCell(firstColumn);
                }
            }
        }
        return null;
    }

    private static boolean isMergedRegion(Sheet sheet, int rowNum, int columnNum) {
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (rowNum >= firstRow && rowNum <= lastRow) {
                if (columnNum >= firstColumn && columnNum <= lastColumn) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int getBigestCellNum(Sheet sheet) {
        int bigestCellNum = 0;
        for (int rowNum = sheet.getFirstRowNum(); rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row != null && row.getLastCellNum() > bigestCellNum) {
                bigestCellNum = row.getLastCellNum();
            }
        }
        return bigestCellNum;
    }

}