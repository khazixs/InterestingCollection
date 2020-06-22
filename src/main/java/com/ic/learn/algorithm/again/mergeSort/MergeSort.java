package com.ic.learn.algorithm.again.mergeSort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSort {

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
        int p1 = left, p2 = mid + 1, k = left;//p1、p2是检测指针，k是存放指针

        while (p1 <= mid && p2 <= right) {
            if (nums[p1] <= nums[p2])
                temp[k++] = nums[p1++];
            else
                temp[k++] = nums[p2++];
        }

        while (p1 <= mid) temp[k++] = nums[p1++];//如果第一个序列未检测完，直接将后面所有元素加到合并的序列中
        while (p2 <= right) temp[k++] = nums[p2++];//同上

        //复制回原素组
        for (int i = left; i <= right; i++)
            nums[i] = temp[i];
    }


    @Test
    public void test() {
        int[] nums = new int[]{2, 4, 5, 3, 1, 6, 9, 8, 7, 0};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
