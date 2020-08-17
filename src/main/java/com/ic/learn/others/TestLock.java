package com.ic.learn.others;

public class TestLock {
    static int id = 0;
    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            new Thread(()->{
                id++;
                System.out.println(id);
            }).start();
        }
    }
}
