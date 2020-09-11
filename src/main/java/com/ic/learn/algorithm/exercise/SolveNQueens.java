package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SolveNQueens {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n==0){
            return res;
        }
        int[] position = new int[n];
        for (int i = 0; i < position.length; i++) {
            position[i] = -n - 1;
        }
        int index = 0;
        help(position, index);
        return res;
    }

    public void help(int[] position, int col) {
        if (col == position.length) {
            for (int e : position) {
                if (e == -2) {
                    return;
                }
            }
            List<String> r = new ArrayList<>();
            for (int p : position) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < position.length; j++) {
                    if (j == p) {
                        sb.append("Q");
                    } else {
                        sb.append(".");
                    }
                }
                r.add(sb.toString());
            }
            res.add(r);
        }
        for (int row = 0; row < position.length; row++) {
            boolean can = true;
            for (int i = 0; i < col; i++) {
                if (position[i] + col - i == row || position[i] == row || position[i] - col + i == row) {
                    can = false;
                    break;
                }
            }
            if (can) {
                position[col] = row;
                help(position, col + 1);
                position[col] = position.length - 1;
            }
        }
    }

    @Test
    public void test() {
        int n = 4;
        List<List<String>> all = new ArrayList<>();
        all = solveNQueens(n);
        for (List<String> list : all) {
            for (String s : list) {
                System.out.println(s);
            }
            System.out.println("______");
        }
    }
}
