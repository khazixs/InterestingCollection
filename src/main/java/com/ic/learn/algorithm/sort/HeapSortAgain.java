package com.ic.learn.algorithm.sort;

import java.util.Arrays;

public class HeapSortAgain {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(heapSort(new int[]{2, 5, 6, 9, 2, 5, 16, 3, 7, 9, 11})));
    }

    public static int[] heapSort(int[] arr) {
        int arrLength = arr.length;
        for (int i = (arrLength - 1) / 2; i >= 0; i--) {
            adjustHeap(arr, i, arrLength);
        }

        for (int i = arrLength - 1; i > 0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;

            adjustHeap(arr, 0, i);
        }
        return arr;
    }

    private static void adjustHeap(int[] arr, int index, int arrLength) {
        /*将当前节点与其子节点中的最大值交换位置*/
        int temp = arr[index];
        int lChildIndex = index * 2 + 1;
        while (lChildIndex < arrLength) {
            int rChildIndex = lChildIndex + 1;
            if (rChildIndex < arrLength && arr[lChildIndex] < arr[rChildIndex]) {
                lChildIndex++;
            }
            if (temp >= arr[lChildIndex]) {
                break;
            }
            arr[index] = arr[lChildIndex];
            index = lChildIndex;
            lChildIndex = lChildIndex * 2 + 1;
        }
        arr[index] = temp;
    }

}
