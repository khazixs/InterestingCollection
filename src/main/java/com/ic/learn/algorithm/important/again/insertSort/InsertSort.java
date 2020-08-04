package com.ic.learn.algorithm.important.again.insertSort;

import java.util.Arrays;

public class InsertSort {
    static int n = 0;
    public static void doInsertSort(int[] array) {
        int length = array.length;
        for (int index = 1; index < length; index++) {//外层向右的index，即作为比较对象的数据的index
            int temp = array[index];//用作比较的数据
            int leftindex = index - 1;
            while (leftindex >= 0 && array[leftindex] > temp) {//当比到最左边或者遇到比temp小的数据时，结束循环
                array[leftindex + 1] = array[leftindex];
                leftindex--;
            }
            array[leftindex + 1] = temp;//把temp放到空位上
            n++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,4,6,5};
        doInsertSort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(n);
    }
}