package cn.yongjie.service;

import cn.yongjie.pojo.Varibale;

import java.util.ArrayList;
import java.util.Map;

public interface DCMService {

    /**
     * @param dcmBasePath
     * @param type
     */
    void modifyDCMFile(String dcmBasePath, String excelPath, String outputPath, String type);


    /**
     * @param mapLines
     * @param variableList
     * @param type
     * @return
     */
    Map<Integer, String> getModifiedLines(Map<Integer, String> mapLines, ArrayList<Varibale> variableList, String type);
}
