package cn.yongjie.utils;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ReadFile {

    public static Map<Integer, String> readTextFile(String a2lPath) {

        Map<Integer, String> lineMaps = new HashMap<Integer, String>();
        Integer lineNum = 1;

        try {
            FileReader fileReader = new FileReader(a2lPath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lineMaps.put(lineNum, line);
                lineNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //System.out.println(lineMaps.size());
        return lineMaps;
    }

    public static void readFromExcel(String excelPath) {

        File file = new File(excelPath);
        try {
            InputStream inputStream = new FileInputStream(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
