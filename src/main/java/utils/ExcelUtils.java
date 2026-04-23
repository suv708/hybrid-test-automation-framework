//package utils;
//
////import org.apache.poi.ss.usermodel.*;
//
//import constants.FrameworkConstants;
//
//import java.io.FileInputStream;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;
//
//import java.io.FileInputStream;
//import java.util.*;
//
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//
//public class ExcelUtils {
//
//    private static final String path =FrameworkConstants.EXCEL_PATH;
//
//    public static List<Map<String, String>> getTestData(String sheetName) {
//
//        List<Map<String, String>> dataList = new ArrayList<>();
//
//        try (FileInputStream fis = new FileInputStream(path);
//             Workbook workbook = Workbook.Factory.create(fis)) {
//
//            Sheet sheet = workbook.getSheet(sheetName);
//
//            if (sheet == null) {
//                throw new RuntimeException("Sheet not found: " + sheetName);
//            }
//
//            DataFormatter formatter = new DataFormatter();
//
//            int rowCount = sheet.getPhysicalNumberOfRows();
//            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
//
//            Row headerRow = sheet.getRow(0);
//
//            for (int i = 1; i < rowCount; i++) {
//
//                Row currentRow = sheet.getRow(i);
//
//                if (currentRow == null) continue;
//
//                Map<String, String> rowMap = new HashMap<>();
//
//                for (int j = 0; j < colCount; j++) {
//
//                    String key = formatter.formatCellValue(headerRow.getCell(j));
//                    String value = formatter.formatCellValue(currentRow.getCell(j));
//
//                    rowMap.put(key, value);
//                }
//
//                dataList.add(rowMap);
//            }
//
//        } catch (Exception e) {
//            throw new RuntimeException("Excel file not found or error reading Excel: " + path, e);
//        }
//
//        return dataList;
//    }
//}


package utils;

import constants.FrameworkConstants;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private static final String path = FrameworkConstants.EXCEL_PATH;

    public static List<Map<String, String>> getTestData(String sheetName) {

        List<Map<String, String>> dataList = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            DataFormatter formatter = new DataFormatter();

            int rowCount = sheet.getPhysicalNumberOfRows();
            Row headerRow = sheet.getRow(0);

            if (headerRow == null) {
                throw new RuntimeException("Header row is missing in sheet: " + sheetName);
            }

            int colCount = headerRow.getPhysicalNumberOfCells();

            for (int i = 1; i < rowCount; i++) {

                Row currentRow = sheet.getRow(i);

                if (currentRow == null) {
                    continue;
                }

                Map<String, String> rowMap = new HashMap<>();

                for (int j = 0; j < colCount; j++) {

                    String key = formatter.formatCellValue(headerRow.getCell(j));
                    String value = formatter.formatCellValue(currentRow.getCell(j));

                    rowMap.put(key, value);
                }

                dataList.add(rowMap);
            }

        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + path, e);
        }

        return dataList;
    }
}