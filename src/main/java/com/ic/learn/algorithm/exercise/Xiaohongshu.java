package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Xiaohongshu {
    /*请完成下面这个函数，实现题目要求的功能
当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
******************************开始写代码******************************/
    static int maxBoxes(int[][] boxes) {
        help(boxes);
        System.out.println(Arrays.deepToString(boxes));
        int res = 0;
        int lastHeight = 0;
        int lastRound = 0;
        for(int start = 0;start<boxes.length;start++){
            int count = 0;
            lastHeight = 0;
            lastRound = 0;
            for (int i = start;i<boxes.length;i++){
                if (lastHeight<boxes[i][0]&&lastRound<boxes[i][1]){
                    count++;
                    lastHeight = boxes[i][0];
                    lastRound = boxes[i][1];
                }
            }
            res = Math.max(res,count);
        }
        return res;
    }
    public static void help(int[][] boxes){
        for (int i = 0;i<boxes.length;i++){
            for (int j = i;j>0;j--){
                if (boxes[j][0]<boxes[j-1][0]){
                    int[] temp = boxes[j-1];
                    boxes[j-1] = boxes[j];
                    boxes[j] = temp;
                }
            }
        }
    }

    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _boxes_rows = 0;
        int _boxes_cols = 0;
        _boxes_rows = Integer.parseInt(in.nextLine().trim());
        _boxes_cols = Integer.parseInt(in.nextLine().trim());

        int[][] _boxes = new int[_boxes_rows][_boxes_cols];
        for(int _boxes_i=0; _boxes_i<_boxes_rows; _boxes_i++) {
            for(int _boxes_j=0; _boxes_j<_boxes_cols; _boxes_j++) {
                _boxes[_boxes_i][_boxes_j] = in.nextInt();

            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }

        res = maxBoxes(_boxes);
        System.out.println(String.valueOf(res));

    }
}
