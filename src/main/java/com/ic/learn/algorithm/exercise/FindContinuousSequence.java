package com.ic.learn.algorithm.exercise;

public class FindContinuousSequence {
    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 3, 4, 5, 6, 7};
        int target = 15;
        findContinuousSequence(array, target);
    }

    private static void findContinuousSequence(int[] array, int target) {
        if (array == null || array.length == 0 || target <= 0) {
            return;
        }
        int small = 0;
        int big = 1;
        int sum = array[small];
        int limit = (target - 1) / 2;
        while (big<array.length){
            sum += array[big];
            while (array[small] <= limit && small < big) {
                if (sum > target) {
                    sum -= array[small];
                    small++;
                } else if (sum < target) {
                    big++;
                    if (big < array.length) {
                        sum += array[big];
                    } else {
                        return;
                    }

                } else {
                    System.out.println("结果：");
                    for (int i = small; i <= big; i++) {
                        System.out.printf("%d ", array[i]);
                    }
                    System.out.print("\n");
                    break;
                }
            }
            big++;

        }

    }

}
