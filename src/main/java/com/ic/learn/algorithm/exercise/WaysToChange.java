package com.ic.learn.algorithm.exercise;

import org.junit.Test;

public class WaysToChange {
    public int waysToChange(int n) {
        Long startTime = System.currentTimeMillis();
        int res = 0;
        for (int n25 = 0; n25 <= n / 25; n25++) {
            int temp1 = n - n25 * 25;
            for (int n10 = 0; n10 <= temp1 / 10; n10++) {
                int temp2 = temp1 - n10 * 10;
                for (int n5 = 0; n5 <= temp2 / 5; n5++) {
                    res++;
                }
            }
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("该方法用时： " + (endTime - startTime));
        return res;
    }

    /*通过很简单的思想先写出代码程序，然后利用数学知识一步步的简化去掉循环，提高速度最总满足要求，开心*/

    public int waysToChange2(int n) {
        Long startTime = System.currentTimeMillis();
        long res = 0;
        long mod = 1000000007;
        for (int n25 = 0; n25 <= n / 25; n25++) {
            long temp = n - n25 * 25;
            res = (res + (((temp / 5 + 1 + (temp % 10) / 5 + 1)*(temp / 10 + 1))/2))%mod;
        }
        Long endTime = System.currentTimeMillis();
        System.out.println("该方法用时： " + (endTime - startTime));
        return (int)res;
    }

    @Test
    public void test() {
        System.out.println(waysToChange(61));
        System.out.println(waysToChange2(900750));
    }
}
