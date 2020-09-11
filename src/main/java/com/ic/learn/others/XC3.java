package com.ic.learn.others;

import java.util.Scanner;

public class XC3 {
    static int res = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int M = sc.nextInt();
        int N= sc.nextInt();
        int E0= sc.nextInt();
        int X= sc.nextInt();
        int L= sc.nextInt();
        int[][] grid = new int[M][N];
        for (int i = 0;i<M;i++){
            for (int j = 0;j<M;j++){
                grid[i][j] = sc.nextInt();
            }
        }
    }
    public static void help(int[][] grid,String direct,int E0,int X,int L,int count,int i,int j){
        if (i<0||j<0||i>=grid.length||j>= grid[0].length){
            return;
        }
        switch (direct){
            case "right":
                for (int k = j;k<grid[0].length;k++){
                    if (E0>grid[i][k]){
                        count++;
                        continue;
                    }else{
                        if (X>0&&L>=grid[i][k]){
                            X--;
                            E0 = L;
                        }
                        help(grid,"down",E0,X,L,count,i,k);
                    }
                }
        }
    }
}
