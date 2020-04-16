package com.ic.learn.algorithm.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*通过滑动窗口的模式得到一个整数数组的连续子数组的和的最大值*/
public class SlidingWindow {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
        List<Integer> source = new ArrayList<>(Arrays.asList(1, -2, 3, 10, -4, 7, 2, -5));
//        while (sc.hasNextInt()) {
//            source.add(sc.nextInt());
//        }
        System.out.println(Arrays.toString(source.toArray()));
        List<Integer> result = slidingWindow(source);
        int sum = 0;
        for (Integer value : result) {
            System.out.print(value + " ");
            sum += value;
        }
        System.out.println("和是：" + sum);
    }

    private static List<Integer> slidingWindow(List<Integer> source) {
        int start = 0;
        int end = 0;
        int maxSum = 0;
        int sum = 0;
        /*1 -2 3*/
        for (int i = 0; i < source.size(); i++) {
            int value = source.get(i);
            if (sum <= 0) {
                sum = value;
                start = i;
                end = i;
            } else {
                sum += value;
            }
            if (sum > maxSum) {
                maxSum = sum;
                end = i;
            }
        }
        return source.subList(start, end + 1);
    }
}
