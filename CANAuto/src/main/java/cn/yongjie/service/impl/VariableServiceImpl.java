package cn.yongjie.service.impl;

import cn.yongjie.dao.FormulaDao;
import cn.yongjie.dao.UnitDao;
import cn.yongjie.dao.VariableDao;
import cn.yongjie.dao.impl.FormulaDaoImpl;
import cn.yongjie.dao.impl.UnitDaoImpl;
import cn.yongjie.dao.impl.VariableDaoImpl;
import cn.yongjie.pojo.Unit;
import cn.yongjie.pojo.Varibale;
import cn.yongjie.service.VariableService;
import cn.yongjie.utils.*;
import com.sun.org.apache.xpath.internal.operations.Variable;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class VariableServiceImpl implements VariableService {

    private VariableDao vd = new VariableDaoImpl();
    private UnitDao ud = new UnitDaoImpl();
    private FormulaDao fd = new FormulaDaoImpl();

    public void batchParseXML(String basePath) {

        ArrayList<String> pavastPathList = new ArrayList<String>();

        FilePathUtils.getFileList(basePath, pavastPathList);
        Iterator<String> iterator = pavastPathList.iterator();
        if (iterator.hasNext()) {

            String fullPavastPath = basePath + "/" + iterator.next();
            System.out.println(fullPavastPath);
            parsePavastXML(fullPavastPath);
        }

    }

    public void parsePavastXML(String pavastPath) {

        // set belongmodule
        String[] split = pavastPath.split("_")[0].split("/");
        String belongModule = split[split.length - 1];
        System.out.println(belongModule);

        Connection connection = null;
        try {
            connection = JDBCUtils.getInstance().connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Document document = ReadXMLByDom4j.getDocument(pavastPath);
            // 获取根节点
            Element root = document.getRootElement();
            Element eleSWDATADICTIONARYSPEC = root.element("SW-SYSTEMS")
                    .element("SW-SYSTEM")
                    .element("SW-DATA-DICTIONARY-SPEC");

            // get SW-UNITS
//            Element eleUnit = eleSWDATADICTIONARYSPEC.element("SW-UNITS");
//            Iterator unitIter = eleUnit.elementIterator();
//            while (unitIter.hasNext()) {
//                Element unitEle = (Element) unitIter.next();
//                Unit unit = parseUnitElement(unitEle);
//                ud.insertSingleObjectIntoUnitTable(connection, unit);
//                System.out.println(unit);
//            }

            // get SW-VARIABLES elements
            Element eleSWVARIABLES = eleSWDATADICTIONARYSPEC.element("SW-VARIABLES");
            Iterator swVaraibleIter = eleSWVARIABLES.elementIterator("SW-VARIABLE");
            while (swVaraibleIter.hasNext()) {
                Element variableEle = (Element) swVaraibleIter.next();
                String category = variableEle.elementText("CATEGORY");

                // VALUE
                if (category.equals("VALUE")) {

                    Varibale variable = parseVariableElement(variableEle);
                    variable.setBelongModule(belongModule);
                    variable.setCategory(category);
                    System.out.println(variable);
                    vd.insertSingleObjectIntoVariableTable(connection, variable);

                } else if (category.equals("Bit")) {
                    continue;
                }
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }

        JDBCUtils.getInstance().Close(null, null, connection);
    }

    public Varibale parseVariableElement(Element variableEle) {

        Varibale variable = new Varibale();
        // shortname  category
        String shortName = variableEle.elementText("SHORT-NAME");

        //
        Element eleSwDataDefProps = variableEle.element("SW-DATA-DEF-PROPS");
        String swAddrMethonRef = eleSwDataDefProps.elementText("SW-ADDR-METHOD-REF");
        String swBaseTypeRef = eleSwDataDefProps.elementText("SW-BASE-TYPE-REF");
        String swCalibritionAcess = eleSwDataDefProps.elementText("SW-CALIBRATION-ACCESS");
        String swCodeSyntaxRef = eleSwDataDefProps.elementText("SW-CODE-SYNTAX-REF");
        String swCompuMethodRef = eleSwDataDefProps.elementText("SW-COMPU-METHOD-REF");
        String swDataConstrRef = eleSwDataDefProps.elementText("SW-DATA-CONSTR-REF");
        String swImplPolicy = eleSwDataDefProps.elementText("SW-IMPL-POLICY");
        String swVariableAccessImplPolicy = eleSwDataDefProps.elementText("SW-VARIABLE-ACCESS-IMPL-POLICY");

        ArrayList<Long> minMaxList = StringUitls.handleConstr(swDataConstrRef);
        Long minHexValue = minMaxList.get(0);
        Long maxHexValue = minMaxList.get(1);

        variable.setShortName(shortName);
        variable.setSwAddrMethonRef(swAddrMethonRef);
        variable.setSwBaseTypeRef(swBaseTypeRef);
        variable.setSwCalibritionAcess(swCalibritionAcess);
        variable.setSwCodeSyntaxRef(swCodeSyntaxRef);
        variable.setSwCompuMethodRef(swCompuMethodRef);
        variable.setSwDataConstrRef(swDataConstrRef);
        variable.setSwImplPolicy(swImplPolicy);
        variable.setSwVariableAccessImplPolicy(swVariableAccessImplPolicy);
        variable.setMinHexValue(minHexValue);
        variable.setMaxHexValue(maxHexValue);

        return variable;
    }

    public Unit parseUnitElement(Element eleUnit) {

        Unit unit = new Unit();

        String markSymbol = eleUnit.elementText("SHORT-NAME");
        String realSymbol = eleUnit.elementText("SW-UNIT-DISPLAY");
        unit.setMarkSymbol(markSymbol);
        unit.setRealSymbol(realSymbol);

        return unit;
    }

    public void updataVariableInfo(Map<Integer, String> lineMaps, ArrayList<String> nameList, Connection connection) {

        for (Integer index : lineMaps.keySet()) {
            String line = lineMaps.get(index);

            if (line.contains("begin MEASUREMENT")) {

                String shortName = lineMaps.get(index + 2).trim();
                if (nameList.contains(shortName)) {
                    String description = lineMaps.get(index + 3).trim();
                    Double minPhyValue = Double.parseDouble(lineMaps.get(index + 8).trim());
                    Double maxPhyValue = Double.parseDouble(lineMaps.get(index + 9).trim());

                    //calc min accuracy
                    String miniAccFormat = lineMaps.get(index + 12).trim().split(" ")[1];
                    Integer accuracy = Integer.parseInt(miniAccFormat.split("\\.")[1].replace("\"", ""));
                    String formula = lineMaps.get(index + 5).trim().toLowerCase();
                    ArrayList<Integer> paramList = fd.getFormulaParamByFormulaName(connection, formula);
                    Double miniAccuracy = 0.0;
                    if (paramList.size() > 0) {
                        miniAccuracy = CalcUitls.getMinAccuracy(paramList, accuracy);
                    }

                    vd.updateFields(connection, shortName, description, minPhyValue, maxPhyValue, miniAccuracy);
                }
            }
        }

    }

    public void parseA2LFile(String a2lPath) {

        // get line maps
        ArrayList<String> a2lPathList = new ArrayList<String>();
        FilePathUtils.getFileList(a2lPath, a2lPathList);
        Map<Integer, String> lineMaps = ReadFile.readTextFile(a2lPath + "/" + a2lPathList.get(0));

        // get all variable by query mysql
        Connection connection = null;
        try {
            connection = JDBCUtils.getInstance().connection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<String> nameList = vd.queryAllVariableShortName(connection);

        //update related variable
        updataVariableInfo(lineMaps, nameList, connection);

        JDBCUtils.getInstance().Close(null, null, connection);

    }
}
