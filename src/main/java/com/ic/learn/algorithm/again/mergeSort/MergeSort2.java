package com.ic.learn.algorithm.again.mergeSort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSort2 {
    public void mergeSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }

    public void merge(int[] nums, int left, int mid, int right) {
        int[] arrays = new int[nums.length];
        int pl = left;
        int pr = mid + 1;
        int put = left;
        while (pl < mid + 1 && pr < right + 1) {
            if (nums[pl] > nums[pr]) {
                arrays[put++] = nums[pr++];
            } else {
                arrays[put++] = nums[pl++];
            }
        }
        while (pl < mid + 1) arrays[put++] = nums[pl++];
        while (pr < right + 1) arrays[put++] = nums[pr++];

        if (right + 1 - left >= 0) System.arraycopy(arrays, left, nums, left, right + 1 - left);
    }

    @Test
    public void test() {
        int[] nums = new int[]{2, 4, 5, 3, 1, 6, 9, 8, 7, 0};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));

    }
}
