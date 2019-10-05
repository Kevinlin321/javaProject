package cn.yongjie.service.impl;

import cn.yongjie.dao.VariableDao;
import cn.yongjie.dao.impl.VariableDaoImpl;
import cn.yongjie.pojo.ExcelInfo;
import cn.yongjie.pojo.Varibale;
import cn.yongjie.service.DCMService;
import cn.yongjie.service.ExcelParseService;
import cn.yongjie.utils.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Map;

public class DCMServiceImpl implements DCMService {

    private VariableDao vd = new VariableDaoImpl();
    private ExcelParseService eps = new ExcelParseServiceImpl();

    public void modifyDCMFile(String dcmBasePath, String excelPath, String outputPath, String type) {

        Connection connection = null;
        try {
            connection = JDBCUtils.getInstance().connection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // get line maps
        ArrayList<String> dcmPathList = new ArrayList<String>();
        FilePathUtils.getFileList(dcmBasePath, dcmPathList);
        Map<Integer, String> lineMaps = ReadFile.readTextFile(dcmBasePath + "/" + dcmPathList.get(0));

        //get can varibale list
        ArrayList<ExcelInfo> infoList = ReadFile.readFromExcel(excelPath);
        ArrayList<String> itfNameList = new ArrayList<String>();
        ArrayList<String> nameList = new ArrayList<String>();

        for (int i = 0; i < infoList.size(); i++) {
            itfNameList.add(StringUitls.modifyRootValue(infoList.get(i).getVariableName()));
            nameList.add(infoList.get(i).getVariableName());
        }
//        System.out.println(StringUitls.mkString(nameList, ","));
        ArrayList<Varibale> variableList = vd.queryVariableMaxMinByShortName(connection,
                StringUitls.mkString(nameList, ","));

//        for (int i = 0; i < variableList.size(); i++) {
//            System.out.println(variableList.get(i));
//        }

        // out put result excel
        eps.updateMaxMinInfoToWorkbook(AppConfig.excalOutPath, variableList, infoList);

        if (type.equalsIgnoreCase("all")) {

            Map<Integer, String> maxLines = getModifiedLines(lineMaps, variableList, "max");
            WriteFileUtils.writeDCMToFiles(maxLines, outputPath + "/max.dcm");

            Map<Integer, String> minLines = getModifiedLines(lineMaps, variableList, "min");
            WriteFileUtils.writeDCMToFiles(minLines, outputPath + "/min.dcm");

            Map<Integer, String> acurracyLines = getModifiedLines(lineMaps, variableList, "accuracy");
            WriteFileUtils.writeDCMToFiles(acurracyLines, outputPath + "/accuracy.dcm");
        } else {
            Map<Integer, String> modifiedLines = getModifiedLines(lineMaps, variableList, type);
            String dcmoutputPath = String.format("%s/%s.dcm", outputPath, type);
            WriteFileUtils.writeDCMToFiles(modifiedLines, dcmoutputPath);
        }

        JDBCUtils.getInstance().Close(null, null, connection);
    }


    public Map<Integer, String> getModifiedLines(Map<Integer, String> mapLines,
                                                 ArrayList<Varibale> variableList,
                                                 String type) {

        for (Integer key : mapLines.keySet()) {
            String line = mapLines.get(key);
            if (line.contains("FESTWERT")) {
//                System.out.println(line);
                for (Varibale variable : variableList) {
                    if (line.contains(variable.getShortName())) {
                        String toBeModifyLine = mapLines.get(key + 4);
                        String afterModifyLine = "";
                        if (type.equalsIgnoreCase("max")) {
                            afterModifyLine = StringUitls.modifyDCMbyGivenValue(toBeModifyLine, variable.getMaxPhyValue());
                        } else if (type.equalsIgnoreCase("min")) {
                            afterModifyLine = StringUitls.modifyDCMbyGivenValue(toBeModifyLine, variable.getMinPhyValue());
                        } else if (type.equalsIgnoreCase("accuracy")) {
                            afterModifyLine = StringUitls.modifyDCMbyGivenValue(toBeModifyLine, variable.getMimiAccuracy());
                        }
                        mapLines.put(key+4, afterModifyLine);
//                        System.out.println(mapLines.get(key+4));
                    }
                }
            }
        }

        return mapLines;
    }
}
