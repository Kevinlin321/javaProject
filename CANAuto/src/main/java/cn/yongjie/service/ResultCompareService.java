package cn.yongjie.service;

import org.apache.poi.ss.usermodel.Workbook;

public interface ResultCompareService {

    /**
     *
     * @param setExcelPath
     * @param testExcelBasePath
     * @param resultPath
     */
    void compareTestAndSetResult(String setExcelPath, String testExcelBasePath, String resultPath);

    /**
     *
     * @param setExcelPath
     * @param workbook
     */
    void transposeWorkbook(String setExcelPath, Workbook workbook);

    /**
     *
     * @param testExcelBasePath
     */
    void compareVarAndArrayResult(String testExcelBasePath);
}
