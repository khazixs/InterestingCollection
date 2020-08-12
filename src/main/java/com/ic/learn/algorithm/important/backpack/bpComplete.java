package com.ic.learn.algorithm.important.backpack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class bpComplete {
    public static void main(String[] args) {
        File file = new File("E:\\project\\InterestingCollection-master\\src\\main\\resources\\doc\\backpack\\bp01.txt");
        try {
            Scanner sc = new Scanner(file);
            int n = sc.nextInt();
            int total = sc.nextInt();
            int[] volumes = new int[n];
            int[] values = new int[n];
            for (int i= 0;i<n;i++){
                volumes[i] = sc.nextInt();
                values[i] = sc.nextInt();
            }
            System.out.println(bpComplete(total,n,volumes,values));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static int bpComplete(int total,int n,int[] volumes,int[] values){
        int[] dp = new int[total + 1];
        for (int i = 1;i<= n;i++){/*每轮循环表示是否选择第i个物品，在体积j下的最大价值*/
            for (int j = 0;j<=total;j++){
                if (j>=volumes[i-1]){
                    dp[j] = Math.max(dp[j],dp[j-volumes[i-1]]+ values[i-1]);
                }
            }
        }
        return dp[total];
    }
}
