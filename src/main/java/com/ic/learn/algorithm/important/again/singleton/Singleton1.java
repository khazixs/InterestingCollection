package com.ic.learn.algorithm.important.again.singleton;

public class Singleton1 {
    private static Singleton1 instance = new Singleton1();
    private Singleton1(){

    }

    public static Singleton1 getInstance(){
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleton1.getInstance() == Singleton1.getInstance());
    }
}
