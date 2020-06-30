package com.ic.learn.algorithm.exercise;

public class ShowDifferenceBetweenIntAndInteger {

    public static void main(String[] args) {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
        /*当我们给一个Integer对象赋一个int值的时候，会调用Integer类的静态方法valueOf
        简单的说，如果整型字面量的值在-128到127之间，那么不会new新的Integer对象，而是直接引用常量池中的Integer对象，
        所以f1==f2的结果是true，而f3==f4的结果是false。*/
        System.out.println(f1 == f2);
        System.out.println(f3 == f4);
    }
}
