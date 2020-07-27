package com.ic.learn.algorithm.exercise;

public class MinNumInRotaryArray {
    public static Integer getMinNum(int[] nums){
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int pivot = low + (high - low) / 2;
            if (nums[pivot] < nums[high]) {
                high = pivot;
            } else if (nums[pivot] > nums[high]) {
                low = pivot + 1;
            } else {
                high -= 1;
            }
        }
        return nums[low];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,2,2,0,1};
        System.out.println(getMinNum(nums));
    }
}
