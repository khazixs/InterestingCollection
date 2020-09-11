package com.ic.learn.others;

import java.util.*;

public class MAIN10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long x = sc.nextLong();
        long y = sc.nextLong();
        long z = sc.nextLong();
        int N = sc.nextInt();
        long xa = sc.nextLong();
        long ya = sc.nextLong();
        long xb = sc.nextLong();
        long yb = sc.nextLong();
        long xc = sc.nextLong();
        long yc = sc.nextLong();
        long nx = x;
        long ny = x;
        long nz = x;
        for (int i = 0; i < N; i++) {
            nx = x + y * xb + z * xc;
            ny = y + x * xa + z * yc;
            nz = z + x * ya + y * yb;
            x = nx;
            y = ny;
            z = nz;
            x = x%1000000007;
            y = y%1000000007;
            z = z%1000000007;
        }
        System.out.println(x + " " + y + " " + z);
    }
}
