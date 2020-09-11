package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class MaxValuePath {
    static int[][] values;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int m = Integer.parseInt(s.substring(0,1));
        int n = Integer.parseInt(s.substring(2,3));
        int[][] data = new int[m][n];
        values = new int[m][n];
        for (int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                data[i][j] = in.nextInt();
            }
        }
        help(data,0,0);
        System.out.println(values[m-1][n-1]);
    }

    public static void help(int[][] data,int i,int j){
        int m = data.length;
        int n = data[0].length;
        if (i>=m||j>=n){
            return;
        }
        int leftValue = j==0?0:values[i][j-1];
        int rightValue = i==0?0:values[i-1][j];
        int maxValue = leftValue>=rightValue?leftValue+data[i][j]:rightValue+data[i][j];
        values[i][j] = maxValue;
        help(data,i+1,j);
        help(data,i,j+1);
    }
}
