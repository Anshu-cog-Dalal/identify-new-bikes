package org.example.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    private static Workbook workbook;

    private static void loadExcel() {
        try {
            if (workbook == null) {
                FileInputStream fis = new FileInputStream(
                        ConfigReader.get("testdata.excel"));
                workbook = new XSSFWorkbook(fis);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Excel file", e);
        }
    }

    public static String getEmail(int row){
        loadExcel();
        Sheet sheet = workbook.getSheet("GoogleLoginTestData");
        String email = sheet.getRow(row).getCell(2).getStringCellValue();
        return email;
    }

}