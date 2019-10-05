package cn.yongjie.service;

import cn.yongjie.pojo.ExcelInfo;
import cn.yongjie.pojo.Varibale;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface ExcelParseService {

    ArrayList<ExcelInfo> getCanNeededVariable(String excelBasePath);

    void updateMaxMinInfoToWorkbook(String outputPath, ArrayList<Varibale> variableList, ArrayList<ExcelInfo> infoList);
}
