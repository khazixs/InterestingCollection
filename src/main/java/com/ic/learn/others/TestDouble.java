package com.ic.learn.others;

import java.math.BigDecimal;

public class TestDouble {
    public static void main(String[] args) {
        double a = 122.12612523352;
        BigDecimal bd = new BigDecimal(a);
        double b = bd.setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(b);
    }
}
