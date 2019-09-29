package cn.yongjie;

import java.math.BigDecimal;

public class DoubleTypeTest {
    public static void main(String[] args) {

        Double a = 0.011;
        Double b = 0.01;
        System.out.println(a.compareTo(b));

        BigDecimal c = new BigDecimal(0.001);
        BigDecimal d = new BigDecimal(0.0011);
        System.out.println(c.compareTo(d));
    }
}
