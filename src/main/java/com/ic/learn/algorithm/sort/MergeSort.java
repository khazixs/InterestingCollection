package com.ic.learn.algorithm.sort;

public class MergeSort {
    static int count = 0;

    public static int inversePairs(int[] array) {
        if (array == null) {
            return 0;
        }
        mergeSort(array, 0, array.length - 1);
        return count;
    }

    private static void mergeSort(int[] data, int start, int end) {
        int mid = (start + end) / 2;
        if (start < end) {
            mergeSort(data, start, mid);
            mergeSort(data, mid + 1, end);
            merge(data, start, mid, end);
        }
    }

    private static void merge(int[] data, int start, int mid, int end) {
        int[] arr = new int[end - start + 1];
        int c = 0;
        int s = start;
        int index = mid + 1;
        while (start <= mid && index <= end) {
            if (data[start] < data[index]) {
                arr[c++] = data[start++];
            } else {
                arr[c++] = data[index++];
                count += mid + 1 - start;
                count %= 1000000007;
            }
        }
        while (start <= mid) {
            arr[c++] = data[start++];
        }
        while (index <= end) {
            arr[c++] = data[index++];
        }
        for (int d : arr) {
            data[s++] = d;
        }
    }

    public static void main(String[] args) {
        System.out.println(inversePairs(new int[]{7, 5, 4, 6}));
    }
}
