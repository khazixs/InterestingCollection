package com.ic.learn.algorithm.exercise;

/*从二维数组的左上角开始只能向下和向右走，求从右下角走出矩阵的最大权重值和*/
public class MaxValueOfGift {
    public static void main(String[] args) {
        int[][] gifMatrix = new int[][]{{1, 10, 3, 8}, {12, 2, 9, 6}, {5, 7, 4, 11}, {3, 7, 16, 5}};
        int[][] maxValueMatrix = new int[4][4];
        System.out.println(getMaxValue(gifMatrix, maxValueMatrix));
    }

    /*动态规划防止重复计算*/
    public static int getMaxValue(int[][] gifMatrix, int[][] maxValueMatrix) {
        if (gifMatrix == null) {
            return 0;
        }
        int rows = maxValueMatrix.length;
        if (rows == 1) {
            int sum = 0;
            for (int value : gifMatrix[0]) {
                sum += value;
            }
            return sum;
        }

        int cols = maxValueMatrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int left = 0;
                int up = 0;
                if (i > 0) {
                    up = maxValueMatrix[i - 1][j];
                }
                if (j > 0) {
                    left = maxValueMatrix[i][j - 1];
                }
                maxValueMatrix[i][j] = Math.max(up, left) + gifMatrix[i][j];
            }
        }
        return maxValueMatrix[rows - 1][cols - 1];
    }
}
