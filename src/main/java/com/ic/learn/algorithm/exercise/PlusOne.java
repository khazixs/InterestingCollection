package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.Arrays;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        boolean over = false;
        int index = digits.length - 1;
        int curData = digits[index];
        curData = curData + 1;
        if (curData >= 10) {
            while (curData >= 10) {
                if (index > 0) {
                    digits[index] = curData-10;
                    digits[index-1] = digits[index-1]+1;
                    index--;
                    curData = digits[index];
                } else {
                    digits[index] = curData-10;
                    over = true;
                    break;
                }
            }
        } else {
            digits[index]++;
        }
        if (over) {
            int[] res = new int[digits.length+1];
            res[0] = 1;
            for (int i = 1;i<res.length;i++){
                res[i] = digits[i-1];
            }
            return res;
        }
        return digits;
    }

    @Test
    public void test(){
        int[] digits = new int[]{1,0,0,9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }
}
