package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MoveAndCross {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String path = sc.nextLine();
        System.out.println(help(path)?"True":"False");
    }

    public static boolean help(String path) {
        int[] temp = new int[]{0, 0};
        List<int[]> list = new ArrayList<>();
        list.add(temp);
        for (int i = 0; i < path.length(); i++) {
            switch (path.charAt(i)) {
                case 'E':
                    temp[0] = temp[0] + 1;
                    break;
                case 'S':
                    temp[1] = temp[1] - 1;
                    break;
                case 'W':
                    temp[0] = temp[0] - 1;
                case 'N':
                    temp[1] = temp[1] + 1;
            }
            if (list.contains(temp)) {
                return true;
            } else {
                list.add(temp);
            }
        }
        return false;
    }
}
