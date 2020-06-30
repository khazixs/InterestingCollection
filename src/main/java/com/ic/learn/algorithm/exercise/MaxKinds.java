package com.ic.learn.algorithm.exercise;

import java.util.Scanner;

public class MaxKinds {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int D = sc.nextInt();
        int[] positions = new int[N];
        int i = 0;
        while (i < N) {
            positions[i] = sc.nextInt();
            i++;
        }
        System.out.println(pickPosition(positions, D));
    }

    private static int pickPosition(int[] positions, int maxDistance) {
        if (maxDistance <= 1) {
            return 0;
        }
        if (positions.length < 3) {
            return 0;
        }
        long count = 0;
        int start = 0;
        int end = 2;
        int temp = 0;
        while (end < positions.length) {
            while (start < end - 1) {
                if (positions[end] - positions[start] <= maxDistance) {
                    temp =start;
                    count += end - start - 1;
                    start++;
                } else {
                    start++;
                }
            }
            end++;
            start = temp;
        }
        /*2147483647*/
        return (int) (count%99997867);
    }
}
