package cn.yongjie.utils;


import cn.yongjie.pojo.ExcelInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ReadFile {

    private static final String XLS = "xls";
    private static final String XLSX = "xlsx";

    public static Map<Integer, String> readTextFile(String a2lPath) {

        Map<Integer, String> lineMaps = new HashMap<Integer, String>();
        Integer lineNum = 1;

        try {
            FileReader fileReader = new FileReader(a2lPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineMaps.put(lineNum, line);
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(lineMaps.size());
        return lineMaps;
    }

    public static ArrayList<ExcelInfo> readFromExcel(String excelPath) {

        File file = new File(excelPath);
        String suffix = getFileNameExtension(excelPath);
        Workbook workbook = null;

        try {
            InputStream inputStream = new FileInputStream(file);
            if (suffix.equalsIgnoreCase(XLS)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (suffix.equalsIgnoreCase(XLSX)) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return parseExcel(workbook);
    }

    private static ArrayList<ExcelInfo> parseExcel(Workbook workbook){

        ArrayList<ExcelInfo> varNameList = new ArrayList<ExcelInfo>();

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            Sheet sheet = workbook.getSheetAt(i);

            if (sheet == null) {
                continue;
            }

            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            if (firstRow == null) {
                System.out.println("解析Excel失败，在第一行没有读取到任何数据！");
            }

            int rowStart = firstRowNum + 1;
            int rowEnd = sheet.getPhysicalNumberOfRows();

            for (int rowNum = rowStart; rowNum < rowEnd; rowNum++) {

                Row row = sheet.getRow(rowNum);
                if (row == null) {
                    continue;
                }
                ExcelInfo excelInfo = convertRowToData(row);
                if (excelInfo == null) {
                    System.out.println("数据不合法");
                }
                varNameList.add(excelInfo);

            }
        }


        return varNameList;
    }

    private static ExcelInfo convertRowToData(Row row) {

        ExcelInfo excelInfo = new ExcelInfo();

        Cell cell;
        int cellNum = 0;

        cell = row.getCell(cellNum);
        String variableName = convertCellValueToString(cell);

        excelInfo.setVariableName(variableName);

        return excelInfo;

    }

    private static String convertCellValueToString(Cell cell) {
        if(cell==null){
            return null;
        }
        String returnValue = null;
        switch (cell.getCellTypeEnum()) {
            case NUMERIC:   //数字
                Double doubleValue = cell.getNumericCellValue();

                // 格式化科学计数法，取一位整数
//                DecimalFormat df = new DecimalFormat("0");
//                returnValue = df.format(doubleValue);
                break;
            case STRING:    //字符串
                returnValue = cell.getStringCellValue().trim();
                break;
            case BOOLEAN:   //布尔
                Boolean booleanValue = cell.getBooleanCellValue();
                returnValue = booleanValue.toString();
                break;
            case BLANK:     // 空值
                break;
            case FORMULA:   // 公式
                returnValue = cell.getCellFormula();
                break;
            case ERROR:     // 故障
                break;
            default:
                break;
        }
        return returnValue;
    }

    private static String getFileNameExtension(String filePath){
        File file = new File(filePath);
        String fileName = file.getName();
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        return suffix;
    }
}
