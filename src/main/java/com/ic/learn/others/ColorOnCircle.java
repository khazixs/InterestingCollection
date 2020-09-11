package com.ic.learn.others;

import java.util.Arrays;
import java.util.Scanner;

public class ColorOnCircle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        int[] banColor = new int[c];
        int[][] cAppear = new int[2][c];
        for (int i = 0;i<2;i++){
            for (int j = 0;j<c;j++){
                cAppear[i][j] = -m-1;
            }
        }

        for (int i = 0;i<n;i++){
            int num_i = sc.nextInt();
            for (int j = 0;j<num_i;j++){
                int color = sc.nextInt() - 1;
                if (banColor[color] == 1){
                    continue;
                }else{
                    int distance1 = Math.min((i - cAppear[0][color]), (Math.abs(cAppear[0][color]) + n - i));
                    int distance2 = Math.min((i - cAppear[1][color]), (Math.abs(cAppear[1][color]) + n - i));
                    if (distance1>m&&distance2>m){
                        if (cAppear[1][color]==-m-1){
                            cAppear[1][color]= cAppear[0][color];
                            cAppear[0][color] = i;
                        }
                    }else{
                        banColor[color] = 1;
                    }
                }

            }
        }
        int res = 0;

        for (int i = 0;i<c;i++){
            if (banColor[i]==1){
                res++;
            }
        }
        System.out.println(Arrays.toString(banColor));
        System.out.println(res);
    }
}
