package com.ic.learn.algorithm.exercise;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*本算法是为了求出以指定规格的滑动窗口中的最大值*/
public class MaxInWindows {
    public static void main(String[] args) {
        int[] data = new int[]{1, 3, 5, 2, 5, 7, 4, 2, 1};
        int size = 3;
        System.out.println(Arrays.toString(maxInWindows(data, size).toArray()));
    }

    private static List<Integer> maxInWindows(int[] data, int size) {
        if (data == null || data.length == 0) {
            return null;
        }
        List<Integer> maxValues = new ArrayList<>();
        if (data.length == 1) {
            maxValues.add(data[0]);
            return maxValues;
        }
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        arrayDeque.addLast(0);
        for (int i = 1; i < data.length; i++) {
            if ((i - arrayDeque.getFirst()) >= size) {
                arrayDeque.pollFirst();
            }
            if (data[i] > data[arrayDeque.getFirst()]) {
                while (!arrayDeque.isEmpty()) {
                    arrayDeque.pollFirst();
                }
                arrayDeque.addLast(i);
            } else if (data[i] < data[arrayDeque.getFirst()]) {
                if (arrayDeque.size() > 1 && data[arrayDeque.getLast()] < data[i]) {
                    arrayDeque.pollLast();
                    arrayDeque.addLast(data[i]);
                } else {
                    arrayDeque.addLast(i);
                }
            }
            if (i >= size-1) {
                maxValues.add(data[arrayDeque.getFirst()]);
            }
        }
        return maxValues;
    }
}
