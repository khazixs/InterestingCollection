package com.ic.learn.algorithm.exercise;

public class Solve {
    int row;
    int col;

    public void solve(char[][] board) {
        if (board == null) {
            return;
        }
        row = board.length;
        if (row == 0) {
            return;
        }
        col = board[0].length;
        for (int i = 0; i < row; i++) {
            dfs(board, i, 0);
            dfs(board, i, col - 1);
        }

        for (int i = 1; i < row-1; i++) {
            dfs(board, 0, i);
            dfs(board, row - 1, i);
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'A') {
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= col || board[i][j] == 'A' || board[i][j] == 'X') {
            return;
        }
        board[i][j] = 'A';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }
}
