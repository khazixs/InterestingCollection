package com.ic.learn.algorithm.exercise.t;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("F:\\project\\InterestingCollection\\src\\main\\resources\\doc\\test.txt"));
//        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < T; i++) {
            String[] s = sc.nextLine().split(" ");
            System.out.println(check(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2])) ? "YES" : "NO");
        }
    }

    private static boolean check(int A, int B, int n) {
        int modFirst = A % 3;
        int modSecond = B % 3;
        if (n == 1) {
            return modFirst == 0;
        }
        if (n == 2) {
            return modSecond == 0;
        }
        int modResult = 0;
        for (int i = 2; i < n; i++) {
            modResult = (modFirst + modSecond) % 3;
            modFirst = modSecond;
            modSecond = modResult;
        }
        return modResult == 0;
    }
}
