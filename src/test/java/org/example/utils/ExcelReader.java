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

    public static Object[][] getSheetData(String sheetName) {
        loadExcel();

        Sheet sheet = workbook.getSheet(sheetName);

        int totalRows = sheet.getLastRowNum();     // excluding header
        int totalCols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[totalRows][totalCols];

        for (int i = 1; i <= totalRows; i++) {
            Row row = sheet.getRow(i);

            for (int j = 0; j < totalCols; j++) {
                Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                data[i - 1][j] = getCellValue(cell);
            }
        }
        return data;
    }

    private static String getCellValue(Cell cell) {

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case BLANK:
                return "";

            default:
                return "";
        }
    }

    public static String getEmail(int row){
        loadExcel();
        Sheet sheet = workbook.getSheet("GoogleLoginTestData");
        String email = sheet.getRow(row).getCell(2).getStringCellValue();
        return email;
    }

}