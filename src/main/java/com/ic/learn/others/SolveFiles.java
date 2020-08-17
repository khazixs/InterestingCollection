package com.ic.learn.others;

import java.text.SimpleDateFormat;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class SolveFiles {
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(6, 6, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.AbortPolicy());
    static Lock lock = new ReentrantLock();
    static int taskId;
    static Logger logger  = Logger.getLogger(String.valueOf(SolveFiles.class));
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0;j<3;j++){
//                    lock.lock();
//                    ++taskId;//不加锁会线程不安全
                    executor.execute(new Task(++taskId));//这里不加锁就不会
//                    lock.unlock();
                }
            }).start();
        }
    }
}

class Task implements Runnable {
    private int id;
    Logger log  = Logger.getLogger(String.valueOf(SolveFiles.class));
    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            long sleepTime = (long) (Math.random() * 1000);
            log.info(Thread.currentThread() + "于" + new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis()) + " 运行，任务号为：" + id);
//            TimeUnit.MILLISECONDS.sleep(sleepTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
