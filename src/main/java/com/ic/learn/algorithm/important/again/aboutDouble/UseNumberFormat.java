package com.ic.learn.algorithm.important.again.aboutDouble;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class UseNumberFormat {
    public static void main(String[] args) {
        NumberFormat nf1 = NumberFormat.getCurrencyInstance();//货币实例
        NumberFormat nf2 = NumberFormat.getPercentInstance();//百分比实例
        NumberFormat nf3 = NumberFormat.getNumberInstance();//数字实例
        nf3.setMaximumFractionDigits(2);//设置小数部分最多两位，四舍五入保留
        nf3.setRoundingMode(RoundingMode.HALF_UP);//设置舍入模式为四舍五入
        double d = 12012.1261;
        System.out.println(nf1.format(d));
        System.out.println(nf2.format(d));
        System.out.println(nf3.format(d));
    }
}
