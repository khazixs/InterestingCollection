package com.ic.learn.algorithm.again.mergeSort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSort3 {
    public void mergeSort(int[] nums, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(nums, start, mid);
            mergeSort(nums, mid + 1, end);
            merge(nums, start, mid, end);
        }
    }

    public void merge(int[] nums, int left, int mid, int right) {
        int[] temp = new int[nums.length];
        int p1 = left, p2 = mid + 1, k = left;
        while (p1 < mid + 1 && p2 < right + 1) {
            if (nums[p1] <= nums[p2]) {
                temp[k++] = nums[p1++];
            } else {
                temp[k++] = nums[p2++];
            }
        }

        while (p1 <= mid) temp[k++] = nums[p1++];
        while (p2 < right + 1) temp[k++] = nums[p2++];

        for (int i = left;i<=right;i++){
            nums[i] = temp[i];
        }
    }

    @Test
    public void test() {
        int[] nums = new int[]{2, 4, 5, 3, 1, 6, 9, 8, 7, 0};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
