package cn.yongjie.utils;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;

public class StringUitls {

    // handle function param like a/b to a and b
    public static ArrayList<Integer> handleFractionParam(String param) {

        ArrayList<Integer> paramList = new ArrayList<Integer>();
        Integer a = 0;
        Integer b = 0;

        if (param.contains("/")) {
            String[] split = param.split("/");
            a = Integer.parseInt(split[0]); //若有double类型，则会爆出异常
            b = Integer.parseInt(split[1]);

        } else {
            a = Integer.parseInt(param);
            b = 1;
        }
        paramList.add(a);
        paramList.add(b);

        return paramList;
    }

    //handle hex max and min
    public static ArrayList<Long> handleConstr(String swDataConstrRef) {

        final String minusSymbol = "_055";
        if (swDataConstrRef.contains("_055")) {
            swDataConstrRef = swDataConstrRef.replace("_055", "-");
        }
        ArrayList<Long> arrayList = new ArrayList<Long>();

        //只能以第一个_分隔
        String minStr = swDataConstrRef.split("_")[1];
        String maxStr = swDataConstrRef.split("_")[2];

        System.out.println(minStr);
        System.out.println(maxStr);

        arrayList.add(Long.parseLong(minStr));
        arrayList.add(Long.parseLong(maxStr));

        return arrayList;
    }
}
