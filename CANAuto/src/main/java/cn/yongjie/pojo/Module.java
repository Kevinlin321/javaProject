package cn.yongjie.pojo;

import java.util.ArrayList;

public class Module {

    private String name;

    private String version;

    private ArrayList<String> systemConditionList;

    private String reversedCol01;

    private String reversedCol02;

    private String reversedCol03;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ArrayList<String> getSystemConditionList() {
        return systemConditionList;
    }

    public void setSystemConditionList(ArrayList<String> systemConditionList) {
        this.systemConditionList = systemConditionList;
    }

    public String getReversedCol01() {
        return reversedCol01;
    }

    public void setReversedCol01(String reversedCol01) {
        this.reversedCol01 = reversedCol01;
    }

    public String getReversedCol02() {
        return reversedCol02;
    }

    public void setReversedCol02(String reversedCol02) {
        this.reversedCol02 = reversedCol02;
    }

    public String getReversedCol03() {
        return reversedCol03;
    }

    public void setReversedCol03(String reversedCol03) {
        this.reversedCol03 = reversedCol03;
    }
}
