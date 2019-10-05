package cn.yongjie.service.impl;

import cn.yongjie.pojo.ExcelInfo;
import cn.yongjie.pojo.Varibale;
import cn.yongjie.service.ExcelParseService;
import cn.yongjie.utils.ReadFile;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelParseServiceImpl implements ExcelParseService {

    public ArrayList<ExcelInfo> getCanNeededVariable(String excelBasePath) {
        return ReadFile.readFromExcel(excelBasePath);
    }

    public void updateMaxMinInfoToWorkbook(String outputPath,
                                           ArrayList<Varibale> variableList,
                                           ArrayList<ExcelInfo> infoList) {

        for (ExcelInfo excelInfo : infoList) {
            for (Varibale variable: variableList){
                if (variable.getShortName().equalsIgnoreCase(excelInfo.getVariableName())) {
                    excelInfo.setMinPhyValue(variable.getMinPhyValue());
                    excelInfo.setMaxPhyValue(variable.getMaxPhyValue());
                    excelInfo.setMimiAccuracy(variable.getMimiAccuracy());
                }
            }
        }

        SXSSFWorkbook workbook = new SXSSFWorkbook();

        Sheet sheet = workbook.createSheet("CANVar");
        sheet.setColumnWidth(0, 5560);
        sheet.setColumnWidth(1, 2560);
        sheet.setColumnWidth(2, 2560);
        sheet.setColumnWidth(3, 2560);

        Row row = null;
        Cell cell = null;

        //set fields row
        Row rowTitle = sheet.createRow(0);

        Cell variableCell = rowTitle.createCell(0);
        variableCell.setCellValue("variableName");

        Cell minPhyValueCell = rowTitle.createCell(1);
        minPhyValueCell.setCellValue("minPhyValue");

        Cell maxPhyValueCell = rowTitle.createCell(2);
        maxPhyValueCell.setCellValue("maxPhyValue");

        Cell mimiAccuracyCell = rowTitle.createCell(3);
        mimiAccuracyCell.setCellValue("mimiAccuracy");

        for (int i = 0; i < infoList.size(); i++) {

            row = sheet.createRow(i + 1);

            cell = row.createCell(0);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(infoList.get(i).getVariableName());

            if (infoList.get(i).getMinPhyValue() != null) {
                cell = row.createCell(1);
                cell.setCellType(CellType.NUMERIC);
                System.out.println(infoList.get(i));
                cell.setCellValue(infoList.get(i).getMinPhyValue());

                cell = row.createCell(2);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(infoList.get(i).getMaxPhyValue());

                cell = row.createCell(3);
                cell.setCellType(CellType.NUMERIC);
                cell.setCellValue(infoList.get(i).getMimiAccuracy());
            }
        }

        try {
            FileOutputStream fos = new FileOutputStream(outputPath);
            workbook.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
