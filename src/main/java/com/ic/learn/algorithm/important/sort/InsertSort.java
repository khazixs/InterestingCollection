package com.ic.learn.algorithm.important.sort;

import java.util.Scanner;

public class InsertSort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] data = sc.nextLine().split(",");
        int[] nums = new int[n];
        for (int i = 0;i<n;i++){
            nums[i] = Integer.parseInt(data[i]);
        }
        int[] res = sort(nums);
        for(int i = 0;i<n-1;i++){
            System.out.print(res[i]);
            System.out.print(",");
        }
        System.out.print(res[n-1]);
    }

    public static int[] sort(int[] nums){
        for (int i = 0;i<nums.length;i++){
            for (int j = i;j>0;j--){
                if (nums[j]<nums[j-1]){
                    int temp = nums[j-1];
                    nums[j-1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}
