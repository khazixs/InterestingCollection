package com.ic.learn.algorithm.exercise;

import java.util.*;

public class HuaWei3 {
    public static double []d=new double[100005];
    public static double []r=new double[100005];
    public static double []s=new double[100005];
    public static double []dp=new double[100005];
    public static int n,m;

    public static int G(int x,int y)
    {
        return m*(x-1)+y;
    }

    public static void main(String[] args)
    {
        Scanner S=new Scanner(System.in);
        n=S.nextInt(); m=S.nextInt();
        for(int i=1;i<=n;i++) for(int j=1;j<=m;j++)
        {
            d[G(i,j)]=S.nextDouble();
            r[G(i,j)]=S.nextDouble();
            s[G(i,j)]=S.nextDouble();
        }
        for(int i=n;i>=1;i--)
        {
            for(int j=m;j>=1;j--)
            {
                if(i==n&&j==m) continue;
                dp[G(i,j)]=(dp[G(i+1,j)]*d[G(i,j)]+dp[G(i,j+1)]*r[G(i,j)]+1)/(1.0-s[G(i,j)]);
            }
        }
        System.out.println(dp[1]);
    }
}
