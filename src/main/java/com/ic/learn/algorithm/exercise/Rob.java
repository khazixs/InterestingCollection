package com.ic.learn.algorithm.exercise;

public class Rob {
    public static void main(String[] args) {
        int[] nums = new int[]{9,8,4,3,2};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        return help(0,nums,0);
    }

    private static int help(int start, int[] nums, int sum) {
        if (start >= nums.length) {
            return sum;
        }
        if (start + 1 < nums.length) {
            return Math.max(help(start + 2, nums, sum + nums[start]), help(start + 3, nums, sum + nums[start + 1]));
        } else {
            return sum + nums[start];
        }
    }
}
