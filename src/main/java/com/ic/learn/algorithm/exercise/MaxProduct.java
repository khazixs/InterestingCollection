package com.ic.learn.algorithm.exercise;
//给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
//用变量保存上一个位置的最大值，最小值，当前位置的最大值取决于，本身值（100），上一个最大值乘以当前值（100*2），上一个最小值乘以当前值（-100*-2），三者中的最大值
public class MaxProduct {
    public static void main(String[] args) {
        System.out.println(maxProduct(new int[]{-2,0,-1}));
        System.out.println(maxProduct(new int[]{0,2}));
        System.out.println(maxProduct(new int[]{0,0}));
        System.out.println(maxProduct(new int[]{-2,-3,0,4}));
        System.out.println(maxProduct(new int[]{-2,-3,-3,-4,-1}));
    }
    public static int maxProduct(int[] nums){
        int n = nums.length;
        if (n == 0) {
            return 0;
        }
        int dpMax = nums[0];
        int dpMin = nums[0];
        int max = nums[0];
        for (int i = 1; i < n; i++) {
            //更新 dpMin 的时候需要 dpMax 之前的信息，所以先保存起来
            int preMax = dpMax;
            dpMax = Math.max(dpMin * nums[i], Math.max(dpMax * nums[i], nums[i]));//无需判断当前状态和之前的状态，直接去三个值的最大值即可
            dpMin = Math.min(dpMin * nums[i], Math.min(preMax * nums[i], nums[i]));
            max = Math.max(max, dpMax);
        }
        return max;
    }
}
