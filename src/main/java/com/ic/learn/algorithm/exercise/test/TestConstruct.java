package com.ic.learn.algorithm.exercise.test;

public class TestConstruct {
    int a;
    int b;
    /*java并不存在构造函数赋值顺序取决于变量声明顺序的情况*/
    public TestConstruct() {
        this.b = 0;
        this.a = b + 2;
    }

    public static void main(String[] args) {
        TestConstruct testConstruct = new TestConstruct();
        System.out.println(testConstruct.a);
        System.out.println(testConstruct.b);
    }
}
