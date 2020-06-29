package com.ic.learn.algorithm.again.mergeSort;

import org.junit.Test;

import java.util.Arrays;

public class MergeSort5 {
    public void mergeSort(int[] nums,int start,int end){
        if (nums==null||nums.length==0||nums.length==1){
            return;
        }
        if (start<end){
            int mid = (start+end)/2;
            mergeSort(nums,start,mid);
            mergeSort(nums,mid+1,end);
            merge(nums,start,mid,end);
        }
    }

    public void merge(int[] nums,int left,int mid ,int right){
        int[] array = new int[nums.length];
        int i = left,j=mid+1,k=left;
        while (i<=mid&&j<=right){
            if (nums[i]<nums[j]){
                array[k++] = nums[i++];
            }else{
                array[k++] = nums[j++];
            }
        }
        while (i<=mid) array[k++] = nums[i++];
        while (j<=right) array[k++] = nums[j++];

        for (int s = left;s<=right;s++){
            nums[s] = array[s];
        }
    }

    @Test
    public void test(){
        int[] nums = new int[]{6,3,1,2,4,5,9,7,8,0};
        mergeSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
