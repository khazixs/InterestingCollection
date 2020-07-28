package com.ic.learn.algorithm.exercise;

public class TestGetMinInStack {
    public static void main(String[] args) {
        GetMinInStack getMinInStack = new GetMinInStack();
        Integer[] sourceData = new Integer[]{3, 4, 2, 1};
        for (Integer data : sourceData) {
            getMinInStack.putData(data);
        }
        System.out.println("最小值： " + getMinInStack.minData());
        System.out.println(getMinInStack.popData());
        System.out.println("最小值： " + getMinInStack.minData());
        System.out.println(getMinInStack.popData());
        System.out.println(getMinInStack.popData());
        System.out.println("最小值： " + getMinInStack.minData());
    }
}
