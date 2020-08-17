package com.ic.learn.algorithm.exercise;

public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,-2};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] nums) {
        int maxNegative = Integer.MIN_VALUE;
        int res = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        int end = 0;
        while (start < nums.length) {
            if (nums[start] < 0) {
                if (nums[start] > maxNegative) {
                    maxNegative = nums[start];
                }
                start++;
                continue;
            }
            end = start;
            while (end < nums.length) {
                if (nums[end] < 0 && nums[end] > maxNegative) {
                    maxNegative = nums[end];
                }
                sum = sum + nums[end];
                if (sum > res) {
                    res = sum;
                }
                if (sum <= 0) {
                    sum = 0;
                    break;
                }
                end++;
            }
            start = end + 1;
        }
        return res > Integer.MIN_VALUE ? res : maxNegative;
    }
}
