package cn.yongjie;

import cn.yongjie.pojo.ExcelInfo;
import cn.yongjie.service.DCMService;
import cn.yongjie.service.impl.DCMServiceImpl;
import cn.yongjie.service.impl.FormulaServiceImpl;
import cn.yongjie.service.impl.VariableServiceImpl;
import cn.yongjie.utils.AppConfig;
import cn.yongjie.utils.ReadFile;
import cn.yongjie.utils.StringUitls;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {

        //
//        String param = "123.1/45";
//        ArrayList<Integer> integers = StringUitls.handleFractionParam(param);
//        System.out.println(integers);

//        FormulaServiceImpl fsi = new FormulaServiceImpl();
//        fsi.parseFormulaXML(AppConfig.formulaBasePath);

//        VariableServiceImpl vsi = new VariableServiceImpl();
//        //vsi.batchParseXML(AppConfig.pavastBasePath);
//        vsi.parseA2LFile(AppConfig.a2lBasePath);

//        ArrayList<ExcelInfo> excelInfos = ReadFile.readFromExcel(AppConfig.excelBasePath);
//        for (int i = 0; i < excelInfos.size(); i++) {
//            System.out.println(excelInfos.get(i));
//        }

        DCMService dcmService = new DCMServiceImpl();
        dcmService.modifyDCMFile(AppConfig.dcmBasePath, AppConfig.excelBasePath, AppConfig.dcmOutputBasePath, "max");
    }
}
