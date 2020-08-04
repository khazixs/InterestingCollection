package com.ic.learn.algorithm.important.again.quickSort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort5 {
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
        int i = start;
        int j = end;
        while (i < j) {
            while (i < j && nums[j] >= base) {
                j--;
            }
            while (i < j && nums[i] <= base) {
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
        int[] nums = new int[]{2, 4, 3, 1, 7, 5, 8, 6, 9, 0};
        quickSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
