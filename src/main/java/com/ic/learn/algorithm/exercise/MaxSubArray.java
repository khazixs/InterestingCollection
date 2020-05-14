package com.ic.learn.algorithm.exercise;

public class MaxSubArray {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,-2};
        System.out.println(maxSubArray(nums));
    }

    public static int maxSubArray(int[] numbers) {
        int maxNegative = Integer.MIN_VALUE;
        int res = Integer.MIN_VALUE;
        int sum = 0;
        int start = 0;
        int end = 0;
        while (start < numbers.length) {
            if (numbers[start] < 0) {
                if (numbers[start] > maxNegative) {
                    maxNegative = numbers[start];
                }
                start++;
                continue;
            }
            end = start;
            while (end < numbers.length) {
                if (numbers[end] < 0 && numbers[end] > maxNegative) {
                    maxNegative = numbers[end];
                }
                sum = sum + numbers[end];
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
