package com.ic.learn.algorithm.exercise;

import java.util.ArrayList;
/*该算法使用来实现求数组的所有子序列的*/

public class Group {
    public static void main(String[] args) {
        int[] a= {1,2,3};
        ArrayList<ArrayList<Integer>> list=getSubArray(a,a.length);
        //输出列表：
        for (ArrayList<Integer> mList : list) {
            for (Integer integer : mList) {
                System.out.print(integer + " ");
            }
            //换行
            System.out.println();

        }
    }
    /*其主要思想是将每一个位置上的数字看成有两种状态0和1，0就代表当前子序列不需要该位置上的数字，1就代表包含，
    因此本例子下的000,001,010,011,100,101,110,111等八种状态*/
    private static ArrayList<ArrayList<Integer>> getSubArray(int[] arr,int length) {
        ArrayList<ArrayList<Integer>> bList=new ArrayList<>();
        int status=0;//表示第几种状态
        int statusLimit=1<<length;//3个数字最多八种状态，即需要小于9
        for (status=0;status<statusLimit;status++) {
            ArrayList<Integer> aList=new ArrayList<>();
            for (int i=0;i<length;i++) {/*按位判断每一个位置上的数字是不是该子序列需要的*/
                if (((1<<i)&status)!=0) {
                    aList.add(arr[i]);
                }
            }
            bList.add(aList);//这里没有重复数字因此可以使用列表，但是若有重复数字考虑去重的时候应该适应set作为集合统计结果
        }
        return bList;
    }
}
