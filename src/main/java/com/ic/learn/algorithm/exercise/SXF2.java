package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class SXF2 {
    static int res = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] grid = new int[4][4];
        for (int i = 0;i<4;i++){
            for (int j = 0;j<4;j++){
                grid[i][j] = sc.nextInt();
            }
        }
        int[][] minValue = new int[4][4];
        int[][] in = new int[4][4];
        help(0,0,0,in,grid);
        System.out.println(res);
    }

    public static void help(int x,int y,int values,int[][] in,int[][] grid){
        if (x>3||x<0||y<0||y>3||in[x][y]==1){
            return;
        }
        values+=grid[x][y];
        in[x][y] = 1;
        if (x==3&&y==3){
            res = Math.min(res,values);
        }
        help(x-1,y,values,in,grid);
        help(x+1,y,values,in,grid);
        help(x,y-1,values,in,grid);
        help(x,y+1,values,in,grid);
        in[x][y] = 0;
    }
}
