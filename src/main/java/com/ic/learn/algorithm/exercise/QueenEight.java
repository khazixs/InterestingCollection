package com.ic.learn.algorithm.exercise;

public class QueenEight {
    public static int num = 0; //累计方案总数
    public static final int MAXQUEEN = 8;//皇后个数，同时也是棋盘行列总数
    public static int[] cols = new int[MAXQUEEN]; //定义cols数组，表示每一列棋子存放的位置数

    public QueenEight() {
        //核心函数
        getArrangement(0);
        System.out.print("\n");
        System.out.println(MAXQUEEN + "皇后问题有" + num + "种摆放方法。");
    }

    public void getArrangement(int columnIndex) {
        //遍历该列所有不合法的行，并用rows数组记录当前列哪一行的位置能存值，哪一行的位置不能存储值，不合法即rows[i]=true
        boolean[] rows = new boolean[MAXQUEEN];
        for (int i = 0; i < columnIndex; i++) {
            rows[cols[i]] = true;/*表示不同行*/
            int d = columnIndex - i;/*表示当前列与已经存过值的列之间的差值即距离*/
            if (cols[i] - d >= 0) {
                rows[cols[i] - d] = true;/*表示不同斜线*/
            }
            if (cols[i] + d <= MAXQUEEN - 1) {
                rows[cols[i] + d] = true;/*表示不同反斜线，即若cols[0]为0表示第一列存储在第一行的位置，而当前若是第4行则d=3，不能存放（3,3）这个反斜线位置*/
            }
        }
        for (int i = 0; i < MAXQUEEN; i++) {
            //判断该行是否合法，不合法就继续循环找位置
            if (rows[i]) continue;
            //若合法就设置当前列合法棋子所在行数
            cols[columnIndex] = i;
            /*继续寻找下一行棋子应该存放的位置即递归*/
            //当前列不为最后一列时
            if (columnIndex < MAXQUEEN - 1) {
                getArrangement(columnIndex + 1);
            } else {
                //直到最后都没有出现问题，则证明找到了正确解，累计方案个数
                num++;
                //打印棋盘信息
                printChessBoard();
            }

        }

    }

    public void printChessBoard() {
        System.out.println("第" + num + "种走法");
        for (int i = 0; i < MAXQUEEN; i++) {
            for (int j = 0; j < MAXQUEEN; j++) {
                if (i == cols[j]) {
                    System.out.print("0 ");
                } else
                    System.out.print("+ ");
            }
            System.out.print("\n");
        }

    }

    public static void main(String args[]) {
        QueenEight queen = new QueenEight();
    }

}
