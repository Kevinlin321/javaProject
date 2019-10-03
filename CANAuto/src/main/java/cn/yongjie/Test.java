package cn.yongjie;

import cn.yongjie.service.impl.FormulaServiceImpl;
import cn.yongjie.service.impl.VariableServiceImpl;
import cn.yongjie.utils.AppConfig;
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

        VariableServiceImpl vsi = new VariableServiceImpl();
        //vsi.batchParseXML(AppConfig.pavastBasePath);
        vsi.parseA2LFile(AppConfig.a2lBasePath);
    }
}
