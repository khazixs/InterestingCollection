package com.ic.learn.concurrent.useExchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
    private static final Exchanger<String> exchanger = new Exchanger<>();

    private static ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        threadPool.execute(() -> {
            try {
                String A = "银行流水A";
                A = exchanger.exchange(A);
                System.out.println("A是：" + A);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            try {
                String B = "银行流水B";
                B = exchanger.exchange(B);
                System.out.println("B是：" + B);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        threadPool.shutdown();
    }
}
