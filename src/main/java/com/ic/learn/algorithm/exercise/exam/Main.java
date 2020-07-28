package com.ic.learn.algorithm.exercise.exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    static String[] num1 = new String[]{"4", "5", "3", "2"};
    static String[] num2 = new String[]{"4", "1", "3", "6"};
    static String[] num3 = new String[]{"6", "2", "1", "5"};
    static String[] num4 = new String[]{"1", "2", "6", "5"};
    static String[] num5 = new String[]{"4", "6", "3", "1"};
    static String[] num6 = new String[]{"4", "2", "3", "5"};
    static boolean over = false;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("F:\\project\\InterestingCollection\\src\\main\\resources\\doc\\testScanner.txt");
        Scanner sc = new Scanner(file);
        int arrayNums = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < arrayNums; i++) {
            int n = Integer.parseInt(sc.nextLine());
            String[][] path = new String[n][n];
            for (int j = 0; j < n; j++) {
                path[j] = sc.nextLine().split("");
            }
            help(path);
        }
    }

    private static void help(String[][] path) {
        if (path == null) {
            return;
        }
        if (path[0].length == 0) {
            return;
        }
        int row = path.length;
        int col = path[0].length;
        System.out.println(row);
        System.out.println(col);
        String now = "6";
        int i = 0;
        int j = 0;
        while (!over) {
            if (path[i][j].equals("S")) {
                path[i][j] = "6";
            }
            if (j + 1 < col && (path[i][j + 1].equals("#") || help3(path, i, j + 1))) {
                now = help2(1, now);
                path[i][j + 1] = now;
                j++;
            } else if (i + 1 < row && (path[i + 1][j].equals("#") || help3(path, i + 1, j))) {
                now = help2(2, now);
                path[i + 1][j] = now;
                i++;
            } else if (i - 1 >= 0 && (path[i - 1][j].equals("#") || help3(path, i - 1, j))) {
                now = help2(3, now);
                path[i - 1][j] = now;
                i--;
            } else if (j - 1 >= 0 && (path[i][j - 1].equals("#") || help3(path, i, j - 1))) {
                now = help2(0, now);
                path[i][j - 1] = now;
                j--;
            }

        }
        for (String[] strings : path) {
            for (int l = 0; l < col; l++) {
                System.out.print(strings[l]);
            }
            System.out.print("\n");
        }
    }

    private static boolean help3(String[][] path, int i, int j) {
        if (path[i][j].equals("E")) {
            over = true;
            return true;
        }
        return false;
    }

    private static String help2(int next, String now) {
        switch (now) {
            case "1":
                return num1[next];/*0上，1右，2下，3左*/
            case "2":
                return num2[next];/*0上，1右，2下，3左*/
            case "3":
                return num3[next];/*0上，1右，2下，3左*/
            case "4":
                return num4[next];/*0上，1右，2下，3左*/
            case "5":
                return num5[next];/*0上，1右，2下，3左*/
            case "6":
                return num6[next];/*0上，1右，2下，3左*/
        }
        return "-1";
    }
}
