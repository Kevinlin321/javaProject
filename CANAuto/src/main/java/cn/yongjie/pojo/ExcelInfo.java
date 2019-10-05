package cn.yongjie.pojo;

public class ExcelInfo {

    private String variableName;

    private Double minPhyValue;

    private Double maxPhyValue;

    private Double mimiAccuracy;

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
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

    @Override
    public String toString() {
        return "ExcelInfo{" +
                "variableName='" + variableName + '\'' +
                ", minPhyValue=" + minPhyValue +
                ", maxPhyValue=" + maxPhyValue +
                ", mimiAccuracy=" + mimiAccuracy +
                '}';
    }
}
