package cn.yongjie.pojo;

public class Formula {

    // Convert to short case
    private String name;
    // Linear(a+bx) Moebius(a+bx)/(c+dx)
    private String type;

    private String unit;

    private Integer a;

    private Integer b;

    private Integer c;

    private Integer d;

    // (a + bx) / (c + dx)
    private String formulaUnfold;

    private String reversedCol01;

    private String reversedCol02;

    private String reversedCol03;

    public String getFormulaUnfold() {
        return formulaUnfold;
    }

    public void setFormulaUnfold() {
        this.formulaUnfold = String.format("(%d + %d * x) / (%d + %d * x)", a, b, c, d);
    }

    @Override
    public String toString() {
        return "Formula{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", unit='" + unit + '\'' +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", formulaUnfold='" + formulaUnfold + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    public Integer getC() {
        return c;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public Integer getD() {
        return d;
    }

    public void setD(Integer d) {
        this.d = d;
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
