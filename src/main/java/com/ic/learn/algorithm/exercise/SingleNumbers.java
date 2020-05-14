package com.ic.learn.algorithm.exercise;

import java.util.Arrays;

public class SingleNumbers {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(singleNumbers(new int[]{1, 2, 3, 5, 1, 2, 5, 6})));
    }

    public static int[] singleNumbers(int[] nums) {
        int res = 0;
        for (int value : nums) {
            res = res ^ value;
        }
        int index = 0;
        /*下边这个循环找到第一个两个数不同的数位*/
        while ((res & 1) == 0) {
            res >>= 1;
            index++;
        }
        int r1 = 0, r2 = 0;
        for (int value : nums) {
            /*将整个数组按照index位是否为1分为两个组分别做异或*/
            if (((value >> index) & 1) == 0) {
                r1 ^= value;
            } else {
                r2 ^= value;
            }
        }
        return new int[]{r1, r2};
    }
}
