package com.ic.learn.algorithm.exercise.test;

public class TestFinal {
   final int a = 11;
}

class TestFinal2 extends TestFinal{
    int a = 12;
}

class TestFinal3{
    public static void main(String[] args) {
        TestFinal2 testFinal2 = new TestFinal2();
        System.out.println(testFinal2.a);
    }
}
