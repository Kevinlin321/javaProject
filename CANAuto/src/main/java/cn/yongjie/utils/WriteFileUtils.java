package cn.yongjie.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class WriteFileUtils {

    public static void writeDCMToFiles(Map<Integer, String> lineMaps, String outputPath) {

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        File file = new File(outputPath);
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);

            for (String line: lineMaps.values()){
                bufferedWriter.write(line + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
