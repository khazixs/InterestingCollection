package com.ic.learn.algorithm.exercise;

import java.util.Arrays;

/*将一个无序数组中奇数部分放置于数组前半部分，偶数部分放置于数组后半部分*/
public class RecorderOddEven {
    public static void main(String[] args) {
        int[] example1 = {};
        int[] example2 = {1, 2, 2};
        int[] example3 = {1, 4, 6, 2, 3, 4, 5, 7, 1, 8, 3, 9, 11, 5, 8, 22};
        int[] example4 = {-1, 203, -5, -6, -7, 0};
        System.out.println(Arrays.toString(recorderOddEven(example1,1)));
        System.out.println(Arrays.toString(recorderOddEven(example2,1)));
        System.out.println(Arrays.toString(recorderOddEven(example3,1)));
        System.out.println(Arrays.toString(recorderOddEven(example4,2)));

    }

    private static int[] recorderOddEven(int[] sourceData,int type) {
        if (sourceData == null || sourceData.length == 0) {
            return sourceData;
        }
        int[] result = new int[sourceData.length];
        int oddIndex = 0;
        int evenIndex = sourceData.length - 1;
        for (int data : sourceData) {
            if (putInFirstHalf(data,type)) {
                result[oddIndex] = data;
                oddIndex++;
            } else {
                result[evenIndex] = data;
                evenIndex--;
            }
        }
        return result;
    }

    private static boolean putInFirstHalf(int data,int type){
        switch (type){
            case 1:return (data & 1) == 1;
            case 2:return data < 0;
            default:return false;
        }

    }
}
