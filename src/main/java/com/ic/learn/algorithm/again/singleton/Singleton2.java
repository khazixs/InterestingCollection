package com.ic.learn.algorithm.again.singleton;

public class Singleton2 {
    private Singleton2(){}

    public static Singleton2 getInstance(){
        return Nested.instance;
    }

    static class Nested{
        private Nested(){

        }
        private static Singleton2 instance = new Singleton2();
    }

    public static void main(String[] args) {
        System.out.println(Singleton2.getInstance());
        System.out.println(Singleton2.getInstance());
    }
}


