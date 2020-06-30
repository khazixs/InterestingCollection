package com.ic.learn.concurrent;

public class TestSynchronized {
    static int a = 0;

    public static synchronized void write(){
        a++;
    }

    public static synchronized int read(){
        return a;
    }

    public static void main(String[] args) {
        for (int i = 0;i<20;i++){
            new Thread(TestSynchronized::write).start();
            new Thread(() -> System.out.println(read())).start();
        }
    }
}


