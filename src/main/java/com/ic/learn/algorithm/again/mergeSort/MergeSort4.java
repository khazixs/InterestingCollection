package com.ic.learn.algorithm.again.mergeSort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSort4 {
    public void mergeSort(int[] nums,int start,int end){
        if (start<end){
            int mid = (start+end)/2;
            mergeSort(nums,start,mid);
            mergeSort(nums,mid+1,end);
            merge(nums,start,mid,end);
        }
    }

    public void merge(int[]nums,int left,int mid,int right){
        int i = left,j=mid+1,k=left;
        int[] arr = new int[nums.length];
        while (i<=mid&&j<=right){
            if (nums[i]<=nums[j]){
                arr[k++] = nums[i++];
            }else{
                arr[k++] = nums[j++];
            }
        }

        while (i<=mid) arr[k++] = nums[i++];
        while (j<=right) arr[k++] = nums[j++];

        if (right + 1 - left >= 0) System.arraycopy(arr, left, nums, left, right + 1 - left);
    }


    @Test
    public void test() {
        int[] nums = new int[]{2, 4, 5, 3, 1, 6, 9, 8, 7, 0};
        mergeSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }
}
