package cn.yongjie.pojo;


import java.math.BigInteger;

public class Varibale {

    private String shortName;

    private String belongModule;

    private String category;

    private String swAddrMethonRef;

    private String swBaseTypeRef;

    private String swCalibritionAcess;

    private String swCodeSyntaxRef;

    private String swCompuMethodRef;

    private String swDataConstrRef;

    private String swImplPolicy;

    private String swVariableAccessImplPolicy;

    private String description;

    private Long minHexValue;

    private Long maxHexValue;

    private Double minPhyValue;

    private Double maxPhyValue;
    //怎么求得最小的精度
    private Double mimiAccuracy;
    //系统常数限制
    private String swSyscond;

    private String reservedCol01;

    private String reservedCol02;

    private String reservedCol03;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getBelongModule() {
        return belongModule;
    }

    public void setBelongModule(String belongModule) {
        this.belongModule = belongModule;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSwAddrMethonRef() {
        return swAddrMethonRef;
    }

    public void setSwAddrMethonRef(String swAddrMethonRef) {
        this.swAddrMethonRef = swAddrMethonRef;
    }

    public String getSwBaseTypeRef() {
        return swBaseTypeRef;
    }

    public void setSwBaseTypeRef(String swBaseTypeRef) {
        this.swBaseTypeRef = swBaseTypeRef;
    }

    public String getSwCalibritionAcess() {
        return swCalibritionAcess;
    }

    public void setSwCalibritionAcess(String swCalibritionAcess) {
        this.swCalibritionAcess = swCalibritionAcess;
    }

    public String getSwCodeSyntaxRef() {
        return swCodeSyntaxRef;
    }

    public void setSwCodeSyntaxRef(String swCodeSyntaxRef) {
        this.swCodeSyntaxRef = swCodeSyntaxRef;
    }

    public String getSwCompuMethodRef() {
        return swCompuMethodRef;
    }

    public void setSwCompuMethodRef(String swCompuMethodRef) {
        this.swCompuMethodRef = swCompuMethodRef;
    }

    public String getSwDataConstrRef() {
        return swDataConstrRef;
    }

    public void setSwDataConstrRef(String swDataConstrRef) {
        this.swDataConstrRef = swDataConstrRef;
    }

    public String getSwImplPolicy() {
        return swImplPolicy;
    }

    public void setSwImplPolicy(String swImplPolicy) {
        this.swImplPolicy = swImplPolicy;
    }

    public String getSwVariableAccessImplPolicy() {
        return swVariableAccessImplPolicy;
    }

    public void setSwVariableAccessImplPolicy(String swVariableAccessImplPolicy) {
        this.swVariableAccessImplPolicy = swVariableAccessImplPolicy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getMinHexValue() {
        return minHexValue;
    }

    public void setMinHexValue(Long minHexValue) {
        this.minHexValue = minHexValue;
    }

    public Long getMaxHexValue() {
        return maxHexValue;
    }

    public void setMaxHexValue(Long maxHexValue) {
        this.maxHexValue = maxHexValue;
    }

    public Double getMinPhyValue() {
        return minPhyValue;
    }

    public void setMinPhyValue(Double minPhyValue) {
        this.minPhyValue = minPhyValue;
    }

    public Double getMaxPhyValue() {
        return maxPhyValue;
    }

    public void setMaxPhyValue(Double maxPhyValue) {
        this.maxPhyValue = maxPhyValue;
    }

    public Double getMimiAccuracy() {
        return mimiAccuracy;
    }

    public void setMimiAccuracy(Double mimiAccuracy) {
        this.mimiAccuracy = mimiAccuracy;
    }

    public String getReservedCol01() {
        return reservedCol01;
    }

    public void setReservedCol01(String reservedCol01) {
        this.reservedCol01 = reservedCol01;
    }

    public String getReservedCol02() {
        return reservedCol02;
    }

    public void setReservedCol02(String reservedCol02) {
        this.reservedCol02 = reservedCol02;
    }

    public String getReservedCol03() {
        return reservedCol03;
    }

    public void setReservedCol03(String reservedCol03) {
        this.reservedCol03 = reservedCol03;
    }

    public String getSwSyscond() {
        return swSyscond;
    }

    public void setSwSyscond(String swSyscond) {
        this.swSyscond = swSyscond;
    }

    @Override
    public String toString() {
        return "Varibale{" +
                "shortName='" + shortName + '\'' +
                ", belongModule='" + belongModule + '\'' +
                ", category='" + category + '\'' +
                ", swAddrMethonRef='" + swAddrMethonRef + '\'' +
                ", swBaseTypeRef='" + swBaseTypeRef + '\'' +
                ", swCalibritionAcess='" + swCalibritionAcess + '\'' +
                ", swCodeSyntaxRef='" + swCodeSyntaxRef + '\'' +
                ", swCompuMethodRef='" + swCompuMethodRef + '\'' +
                ", swDataConstrRef='" + swDataConstrRef + '\'' +
                ", swImplPolicy='" + swImplPolicy + '\'' +
                ", swVariableAccessImplPolicy='" + swVariableAccessImplPolicy + '\'' +
                ", description='" + description + '\'' +
                ", minHexValue=" + minHexValue +
                ", maxHexValue=" + maxHexValue +
                ", minPhyValue=" + minPhyValue +
                ", maxPhyValue=" + maxPhyValue +
                ", mimiAccuracy=" + mimiAccuracy +
                ", swSyscond='" + swSyscond + '\'' +
                '}';
    }
}


