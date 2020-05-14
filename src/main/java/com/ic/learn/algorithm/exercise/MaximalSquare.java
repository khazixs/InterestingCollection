package com.ic.learn.algorithm.exercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class MaximalSquare {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("F:\\project\\InterestingCollection\\src\\main\\resources\\doc\\maximalSquare.txt"));
        int rows = Integer.parseInt(sc.nextLine());
        char[][] matrix = new char[rows][];
        for (int i = 0; i < rows; i++) {
            matrix[i] = sc.nextLine().replaceAll(" ", "").toCharArray();
        }
        System.out.println(maximalSquare(matrix));
    }

    public static int maximalSquare(char[][] matrix) {
        if (matrix == null) {
            return 0;
        }
        if (matrix.length == 0) {
            return 0;
        }
        int def = 0;
        if (matrix.length == 1) {
            for (char v : matrix[0]) {
                if (v == '1') {
                    def = 1;
                }
            }
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int res = 0;
        int row1 = 0;
        int col1 = 0;

        while (row1 < rows - 1) {
            if (!visited[row1][col1] && matrix[row1][col1] == '1') {
                def = 1;
                visited[row1][col1] = true;
                int row2 = row1 + 1;
                int col2 = col1 + 1;
                while (row2 < rows && col2 < cols) {
                    int failCount = 0;
                    if (matrix[row2][col2] == '1') {
                        for (int i = row1; i < row2; i++) {
                            if (!(matrix[i][col2] == '1')) {
                                visited[i][col2] = true;
                                if (i > 0) {
                                    visited[i - 1][col2] = true;
                                }
                                failCount++;
                            }
                        }
                        for (int j = col1; j < col2; j++) {
                            if (!(matrix[row2][j] == '1')) {
                                visited[row2][j] = true;
                                if (j > 0) {
                                    visited[row2][j - 1] = true;
                                }
                                failCount++;
                            }
                        }
                    } else {
                        visited[row2][col2] = true;
                        visited[row2 - 1][col2] = true;
                        visited[row2][col2 - 1] = true;
                        break;
                    }
                    if (failCount > 0) {
                        break;
                    }
                    res = Math.max(res, (row2 - row1 + 1) * (col2 - col1 + 1));
                    row2++;
                    col2++;
                }
            }
            if (col1 < cols - 1) {
                col1++;
            } else {
                row1++;
                col1 = 0;
            }
            if(row1 == rows-2){
                char[] cs = matrix[row1+1];
                System.out.println(Arrays.toString(cs));
                for(char c : cs){
                    if(c=='1'){
                        def = 1;
                        break;
                    }
                }
            }
        }
        return Math.max(res,def);
    }
}
