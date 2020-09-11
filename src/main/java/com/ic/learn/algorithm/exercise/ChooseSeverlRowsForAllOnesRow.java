package com.ic.learn.algorithm.exercise;

import com.ic.learn.algorithm.important.sort.HeapSort;
import jdk.nashorn.internal.ir.IfNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChooseSeverlRowsForAllOnesRow {
    static boolean res = false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean result = false;
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] data = new int[n][m];
        for (int i = 0;i<n;i++){
            for (int j = 0;j<m;j++){
                data[i][j]=sc.nextInt();
            }
        }
        help(data,0,new ArrayList<>());
        System.out.println(res?"YES":"NO");
    }

    public static void help(int[][] data, int col, List<Integer> rowsChoose){

        int count = 0;
        for (int i = 0;i<rowsChoose.size();i++){
            if (data[rowsChoose.get(i)][col] == 1){
                count++;
            }
            if (count>1){
                return;
            }
        }

        if (count == 0){
            for (int i = 0;i< data.length;i++){
                if (data[i][col]==1){
                    rowsChoose.add(i);
                    int index = rowsChoose.size()-1;
                    if (col== data[0].length-1){
                        break;
                    }
                    help(data,col+1,rowsChoose);
                    rowsChoose.remove(index);

                }
            }
        }
        if (col== data[0].length-1){
            for (int i = 0;i<data[0].length;i++){
                int c = 0;
                for (int j = 0;j<rowsChoose.size() ;j++){
                    if (data[rowsChoose.get(j)][i]==1){
                        c++;
                    }
                }
                if (c!=1){
                    return;
                }
            }
            res = true;
        }else{
            help(data,col+1,rowsChoose);
        }

    }
}
