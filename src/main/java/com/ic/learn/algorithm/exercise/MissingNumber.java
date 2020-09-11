package com.ic.learn.algorithm.exercise;

public class MissingNumber {
    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{0,1,2,3,4}));
    }

    public static int missingNumber (int[] nums) {
        int length = nums.length;
        int[] sign = new int[length+1];
        for (int num : nums) {
            sign[num] = 1;
        }
        for (int i = 0;i<=length;i++){
            if (sign[i]==0){
                return i;
            }
        }
        return -1;
    }
}
