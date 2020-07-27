package com.ic.learn.algorithm.exercise;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.junit.Test;

public class MinPathSum {
    public Integer minPathSum(int[][] grid) {
        if (grid == null) {
            return -1;
        }
        int row = grid.length;
        if (row == 0) {
            return -1;
        }
        int col = grid[0].length;
        int[][] gs = new int[row][col];
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (x == 0 && y == 0) {
                    gs[x][y] = grid[x][y];
                } else if (x == 0) {
                    gs[x][y] = grid[x][y] + gs[x][y - 1];
                } else if (y == 0) {
                    gs[x][y] = grid[x][y] + gs[x - 1][y];
                } else {
                    gs[x][y] = grid[x][y] + Math.min(gs[x][y - 1], gs[x - 1][y]);
                }
            }
        }
        return gs[row - 1][col - 1];
    }

    @Test
    public void test() {
        int[][] grid = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
        System.out.println(minPathSum(grid));
    }
}
