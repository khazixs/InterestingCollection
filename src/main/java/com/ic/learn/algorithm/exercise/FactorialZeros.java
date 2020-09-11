package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class FactorialZeros {

    static int[] zeros;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        zeros = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            help(i);
        }
        System.out.println(zeros[n]);
    }

    public static int help(int i) {
        if (i < 5) {
            return 0;
        }
        int count = 0;
        if (zeros[i] != 0) {
            count += zeros[i];
        } else {
            int k = i / 5;
            count = k + help(k);
            zeros[i] = count;
        }
        return count;
    }

}
