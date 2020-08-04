package com.ic.learn.algorithm.important.Search;

/*查找有序数组中只有一个重复出现的数字的个数{1,2,3，3，3，3，4,5,6}*/
public class BinarySearchApplication1 {


    public static void main(String[] args) {
        int[] array = new int[]{1, 2, 3, 3, 3, 3, 4, 5, 6};
        System.out.println(search1(array, 0, array.length - 1, 3));
        System.out.println(search2(array, 0, array.length - 1, 3));
    }

    private static int search1(int[] array, int start, int end, int target) {
        if (array == null || array.length == 0 || start > end || start < 0 || end >= array.length) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (array[mid] > target) {
            return search1(array, start, mid - 1, target);
        } else if (array[mid] < target) {
            return search1(array, mid + 1, end, target);
        } else {
            if (mid - 1 >= 0) {
                return array[mid - 1] == target ? search1(array, start, mid - 1, target) : mid;
            } else {
                return mid;
            }
        }
    }

    private static int search2(int[] array, int start, int end, int target) {
        if (array == null || array.length == 0 || start > end || start < 0 || end >= array.length) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (array[mid] > target) {
            return search2(array, start, mid - 1, target);
        } else if (array[mid] < target) {
            return search2(array, mid + 1, end, target);
        } else {
            if (mid + 1 < array.length) {
                return array[mid + 1] == target ? search2(array, mid + 1, end, target) : mid;
            } else {
                return mid;
            }
        }
    }
}
