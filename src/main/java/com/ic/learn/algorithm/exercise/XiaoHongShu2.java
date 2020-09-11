package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class XiaoHongShu2 {
    static List<Integer> listRows = new ArrayList<>();
    static List<Integer> listCols = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int r = sc.nextInt();
        int c = sc.nextInt();
        int res= Integer.MAX_VALUE;
        int[][] data = new int[n][m];
        for (int i = 0;i<n;i++){
            for (int j = 0;j<m;j++){
                data[i][j] = sc.nextInt();
            }
        }
        // TODO: 2020/8/31 r行c列子矩阵
    }

    
    public static int help(int[][] nums){
        int row = nums.length;
        int col = nums[0].length;
        int res = 0;
        for (int i = 0;i<row;i++){
            for (int j = 0;j<col;j++){
                int value = nums[i][j];
                res += i>0?Math.abs(value-nums[i-1][j]):0;
                res += j>0?Math.abs(value-nums[i][j-1]):0;
                res += j<col-1?Math.abs(value-nums[i][j+1]):0;
                res += i<col-1?Math.abs(value-nums[i+1][j]):0;
            }
        }
        return res;
    }
}
