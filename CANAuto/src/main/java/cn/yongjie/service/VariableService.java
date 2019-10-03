package cn.yongjie.service;

import cn.yongjie.pojo.Unit;
import cn.yongjie.pojo.Varibale;
import org.dom4j.Element;

import java.sql.Connection;
import java.util.Map;
import java.util.ArrayList;

public interface VariableService {

    /**
     * parse single XML
     * @param pavastPath
     */
    public void parsePavastXML(String pavastPath);

    /**
     * parse bath XML
     * @param basePath
     */
    public void batchParseXML(String basePath);

    /**
     * parse element XML
     * @param variableEle
     * @return
     */
    public Varibale parseVariableElement(Element variableEle);

    /**
     *
     * @param eleUnit
     * @return
     */
    public Unit parseUnitElement(Element eleUnit);

    /**
     *解析A2L文件
     * @param a2lBasePath
     */
    public void parseA2LFile(String a2lBasePath);

    /**
     *
     * @param lineMaps
     * @param nameList
     */
    public void updataVariableInfo(Map<Integer, String> lineMaps, ArrayList<String> nameList, Connection connection);
}
