package cn.yongjie.pojo;

public class Unit {

    private String markSymbol;

    private String realSymbol;

    private String reversedCol01;

    private String reversedCol02;

    private String reversedCol03;

    public String getMarkSymbol() {
        return markSymbol;
    }

    public void setMarkSymbol(String markSymbol) {
        this.markSymbol = markSymbol;
    }

    public String getRealSymbol() {
        return realSymbol;
    }

    public void setRealSymbol(String realSymbol) {
        this.realSymbol = realSymbol;
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

    @Override
    public String toString() {
        return "Unit{" +
                "markSymbol='" + markSymbol + '\'' +
                ", realSymbol='" + realSymbol + '\'' +
                '}';
    }
}
