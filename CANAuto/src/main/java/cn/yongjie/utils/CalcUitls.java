package cn.yongjie.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CalcUitls {

    // (a / b) + (c / d)x =>(ad + bc*x)/(bd + 1*x)
    public static ArrayList<Integer> changeLinearToMoebius(Integer a, Integer b, Integer c, Integer d) {

        ArrayList<Integer> paramList = new ArrayList<Integer>();

        paramList.add(a * d);
        paramList.add(b * c);
        paramList.add(b * d);
        paramList.add(1);

        return paramList;

    }

    // get min and max phy value
    // hex = (a + b * phy) / (c + d * phy)
    // phy = (a - hex * c) / (hex * d - b)
    // ignore accuracy use a2l file to correct
    public static ArrayList<Double> getMinMaxPhyValue(ArrayList<Long> hexminMaxList, ArrayList<Integer> paramList) {

        ArrayList<Double> minMaxPhyList = new ArrayList<Double>();

        for (int i = 0; i < hexminMaxList.size(); i++) {
            Long value = hexminMaxList.get(i);
            float molecular = (float) (paramList.get(0) - paramList.get(2) * value);
            float denominator = (float) (paramList.get(3) * value - paramList.get(1));
        }

        return minMaxPhyList;
    }

    public static Double getMinAccuracy(ArrayList<Integer> paramList, Integer accuracy) {

        Integer value = 1;
        Integer molecular =  paramList.get(0) - paramList.get(2) * value;
        Integer denominator = paramList.get(3) * value - paramList.get(1);
        Double minaccuracy = molecular.doubleValue() /denominator;

        String format = "#.";
        for (Integer i = 0; i < accuracy; i++) {
            format = format + "0";
        }
        DecimalFormat decimalFormat = new DecimalFormat(format);
        minaccuracy = Double.parseDouble(decimalFormat.format(minaccuracy));

        System.out.println(minaccuracy);
        return minaccuracy;
    }
}
