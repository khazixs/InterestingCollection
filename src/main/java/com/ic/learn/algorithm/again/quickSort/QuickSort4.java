package com.ic.learn.algorithm.again.quickSort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort4 {
    public void quickSort(int[] nums) {
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return;
        }
        sort(nums, 0, nums.length - 1);
    }

    public void sort(int[] nums, int start, int end) {
        if (start > end) {
            return;
        }
        int base = nums[start];
        int i = start, j = end;
        while (i != j) {
            while (nums[j] >= base && i < j) {
                j--;
            }
            while (nums[i] <= base && i < j) {
                i++;
            }
            if (i < j) {
                int t = nums[i];
                nums[i] = nums[j];
                nums[j] = t;
            }
        }
        nums[start] = nums[i];
        nums[i] = base;
        sort(nums, start, i - 1);
        sort(nums, i + 1, end);
    }

    @Test
    public void test() {
        int[] nums = new int[]{6, 3, 1, 2, 4, 5, 9, 7, 8, 0};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
