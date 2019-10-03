package cn.yongjie.service.impl;

import cn.yongjie.dao.impl.FormulaDaoImpl;
import cn.yongjie.pojo.Formula;
import cn.yongjie.service.FormulaService;
import cn.yongjie.utils.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class FormulaServiceImpl implements FormulaService {

    private FormulaDaoImpl fdi = new FormulaDaoImpl();

    /*
        <Formula name="a_div_v_q0p1526" type="Linear" unit="m^2/m^3">
            <Comment></Comment>
            <Parameter value="0"/>
            <Parameter value="4096/625"/>
	    </Formula>
    */
    public void parseFormulaXML(String basePath) {

        Connection connection = null;
        try {
            connection = JDBCUtils.getInstance().connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {

            Document document = ReadXMLByDom4j.getDocument(basePath);
            Element rootElement = document.getRootElement();

            Iterator formulaIter = rootElement.elementIterator("Formula");
            while (formulaIter.hasNext()) {

                Element formulaEle = (Element) formulaIter.next();
                // name type and unit
                String name = formulaEle.attribute("name").getText().toLowerCase();
                String type = formulaEle.attribute("type").getText();
                String unit = formulaEle.attribute("unit").getText();

                //parameters
                Iterator paramIter = formulaEle.elementIterator("Parameter");
                ArrayList<String> paramStrList = new ArrayList<String>();
                while (paramIter.hasNext()) {
                    Element paramEle = (Element) paramIter.next();
                    paramStrList.add(paramEle.attribute("value").getText());
                }
                ArrayList<Integer> paramList = parseParamNodes(paramStrList, type);
                // 针对一部分公式有分数
                if (paramList == null) {
                    continue;
                }

                // create formula instance
                Formula formula = new Formula();
                formula.setName(name);
                formula.setType(type);
                formula.setUnit(unit);
                formula.setA(paramList.get(0));
                formula.setB(paramList.get(1));
                formula.setC(paramList.get(2));
                formula.setD(paramList.get(3));
                formula.setFormulaUnfold();

                System.out.println(formula);
                fdi.insertSingleObjectIntoFormulaTable(connection, formula);

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        JDBCUtils.getInstance().Close(null, null, connection);

    }

    public ArrayList<Integer> parseParamNodes(ArrayList<String> paramStrList, String type) {

        ArrayList<Integer> paramList = new ArrayList<Integer>();

        if (type.equals("Linear")) {

            ArrayList<Integer> tempList0 = StringUitls.handleFractionParam(paramStrList.get(0));
            ArrayList<Integer> tempList1 = StringUitls.handleFractionParam(paramStrList.get(1));
            paramList = CalcUitls.changeLinearToMoebius(tempList0.get(0), tempList0.get(1), tempList1.get(0), tempList1.get(1));
        } else if (type.equals("Moebius")) {

            for (int i = 0; i < paramStrList.size(); i++) {
                try{
                    paramList.add(Integer.parseInt(paramStrList.get(i)));
                } catch (Exception e){
                    //e.printStackTrace();
                    return null;
                }
            }
        } else {
            paramList.addAll(Arrays.asList(0, 0, 0, 0));
        }

        return paramList;
    }
}
