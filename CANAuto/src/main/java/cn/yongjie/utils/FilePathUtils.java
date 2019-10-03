package cn.yongjie.utils;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class FilePathUtils{

    // get pavast list
    public static void getFileList(String basePath, ArrayList<String> fileNameList){

        File file = new File(basePath);
        File[] fileList = file.listFiles();

        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                fileNameList.add(fileList[i].getName());
            }
            if (fileList[i].isDirectory()) {
                getFileList(fileList[i].getAbsolutePath(), fileNameList);
            }
        }
    }


}
