package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TX3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        for (int i = 0; i < list.size(); i++) {
            int temp = list.get(i);
            list.remove(i);
            int length = list.size();
            if (length % 2 == 0) {
                System.out.println((list.get(length / 2) + list.get(length / 2 + 1)) / 2);
            } else {
                System.out.println(list.get(length / 2));
            }
            list.add(i, temp);
        }
    }
}
