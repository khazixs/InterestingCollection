package com.ic.learn.others;


import java.util.Scanner;

public class XM2 {
    static boolean res = false;
    public static void main(String[] args){
        char[][] data = new char[][]{{'A','B','C','E'},{'S','F','C','S'}, {'A','D','E','E'}};
        Scanner sc = new Scanner(System.in);
        String target = sc.nextLine();
        System.out.println(help(data,target));
    }

    public static boolean help(char[][] data,String target){

        int row = data.length;
        int col = data[0].length;
        if (target==null||target.length()==0||target.length()>row*col){
            return false;
        }
        char[] ts = target.toCharArray();
        for (int i = 0;i<row;i++){
            for (int j = 0;j<col;j++){
                if (data[i][j]==ts[0]){
                    int[][] in = new int[row][col];
                    search(data,ts,i,j,0,in);
                }
            }
        }
        return res;
    }

    public static void search(char[][] data,char[] ts,int r,int c,int index,int[][] in){
        if (index==ts.length){
            res = true;
            return;
        }
        if (r>=data.length||c>=data[0].length||r<0||c<0){
            return;
        }
        if (data[r][c] == ts[index]&&in[r][c]==0){
            in[r][c] =1;
            search(data,ts,r-1,c,index+1,in);
            search(data,ts,r+1,c,index+1,in);
            search(data,ts,r,c-1,index+1,in);
            search(data,ts,r,c+1,index+1,in);
        }
        in[r][c] = 0;
    }
}
