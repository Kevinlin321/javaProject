package cn.yongjie.service;


import java.util.Iterator;
import java.util.ArrayList;

public interface FormulaService {

    /**
     *
     * @param basePath
     */
    public void parseFormulaXML(String basePath);

    /**
     *
     * @param paramStrList
     * @param type
     * @return
     */
    public ArrayList<Integer> parseParamNodes(ArrayList<String> paramStrList, String type);
}
