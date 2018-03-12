package com.minsx.framework.common.excel;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.List;

public class ExcelTest {

    private ExcelReader excelReader;

    @Before
    public void initial() throws FileNotFoundException {
        excelReader = new ExcelReader(ExcelTest.class.getClassLoader().getResourceAsStream("测试.xlsx"));
    }

    @Test
    public void getAllSheetData() {
        List<Sheet> dataList = excelReader.getAllSheet();
        System.out.println(JSON.toJSONString(dataList));
    }

    @Test
    public void getAllSheetDataWithDataMapper() {
        List<Sheet> newDataList = excelReader.getAllSheet(oldCell -> {
            Cell newCell = new Cell(oldCell);
            if ("姓名".equals(oldCell.getColumnName())) {
                newCell.setValue("新姓名是:" + oldCell.getValue());
            }
            return newCell;
        });
        System.out.println(JSON.toJSONString(newDataList));
    }

    @Test
    public void getSheetData() {
        Sheet sheet = excelReader.getSheet(0);
        System.out.println(JSON.toJSONString(sheet));
    }

    @Test
    public void getRowData() {
        Row row = excelReader.getRow(0, 0);
        System.out.println(JSON.toJSONString(row));
    }

    @Test
    public void getCellData() {
        Cell cell = excelReader.getCell(0, 0, 0);
        System.out.println(JSON.toJSONString(cell));
    }

    @Test
    public void getSheetDataWithDataMapper() {
        Sheet newSheet = excelReader.getSheet(0, oldCell -> {
            Cell newCell = new Cell(oldCell);
            if ("姓名".equals(oldCell.getColumnName())) {
                newCell.setValue("新姓名是:" + oldCell.getValue());
            }
            return newCell;
        });
        System.out.println(JSON.toJSONString(newSheet));
    }

    @Test
    public void getBeansOfSheet() {
        List<Student> students = excelReader.getBeansOfSheet(1, oldRowData -> {
            Student student = new Student();
            oldRowData.getCells().forEach(oldCellData -> {
                if ("姓名".equals(oldCellData.getColumnName())) {
                    student.setName(oldCellData.getValue());
                } else if ("年龄".equals(oldCellData.getColumnName())) {
                    student.setAge(oldCellData.getValue());
                } else if ("性别".equals(oldCellData.getColumnName())) {
                    student.setSex(oldCellData.getValue());
                }
            });
            return student;
        });
        System.out.println(JSON.toJSONString(students));
    }

}
