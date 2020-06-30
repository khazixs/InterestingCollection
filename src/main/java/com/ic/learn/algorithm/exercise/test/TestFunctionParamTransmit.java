package com.ic.learn.algorithm.exercise.test;

public class TestFunctionParamTransmit {
    public static void main(String[] args) {
        A data = new A();
        data.a = 10;
        change(data);
        System.out.println(data.a);
        String data2 = "dasda";
        change(data2);
        System.out.println(data2);
    }
    /*函数是将引用作为参数传递的*/
    public static void change(A data){
        data.a = 11;
    }
    public static void change(String data){
        /*但是在对String进行改变的时候，生成的新的字符串是新建立的字符串所以地址都改变了*/
        data += "hello";
    }
    static class A{
        int a;
    }
}
