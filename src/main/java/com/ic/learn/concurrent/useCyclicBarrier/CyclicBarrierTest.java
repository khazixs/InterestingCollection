package com.ic.learn.concurrent.useCyclicBarrier;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    /*同步屏障*/
    static CyclicBarrier c = new CyclicBarrier(2);

    public static void main(String[] args) {
        new Thread(()->{
            try {
                c.await();//只有规定数量的线程阻塞在c.await处才会解除同步,但是不能保证解除阻塞之后的运行顺序
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(1);
        }).start();
        try {
            c.await();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(2);
    }
}
