package com.ic.learn.algorithm.important.again.aboutDouble;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*double类型数据不可以直接比较和计算，因为计算机保留位数的误差可能导致结果出错
* 而是要使用其计算工具BigDecimal*/

public class DoubleCalculate {
    public static void main(String[] args) {
        double d1 = 10.123;
        double d2 = 9.223;
        BigDecimal b3 = new BigDecimal('a');//BigDecimal可以接受数字字符串及各种类型的数据作为参数
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);

        /*减法*/
        System.out.println(b1.subtract(b2).setScale(3,BigDecimal.ROUND_HALF_UP));
        /*除法,可以使用setScale进行舍入*/
        System.out.println(b1.divide(b2, RoundingMode.HALF_UP).setScale(3,BigDecimal.ROUND_HALF_UP));
        /*加法*/
        System.out.println(b1.add(b2).doubleValue());
        /*返回1表示b2大于b1   -1表示小于   0表示相等*/
        System.out.println(b2.compareTo(b1));
        /*返回当前数据的字符串*/
        System.out.println(b1.toString());
    }
}
