package com.ic.learn.algorithm.exercise;

public class SingleNumber3 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,3,4,1,4};
        System.out.println(singleNumber(nums));
    }
    public static int singleNumber(int[] nums){
        int s = 0;
        for (int v:nums){
            s = s^v;
        }
        return s;
    }
}
