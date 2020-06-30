package com.ic.learn.concurrent.useDelayQueue;


import com.ic.learn.concurrent.useDelayQueue.MyDelayedEvent;
import com.ic.learn.concurrent.useDelayQueue.MyDelayedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class MyDelayedServiceImpl implements MyDelayedService {
    private static final Logger logger = LoggerFactory.getLogger(MyDelayedService.class);
    private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DelayQueue<MyDelayedEvent> queue = new DelayQueue<>();
    private Executor executor = Executors.newFixedThreadPool(30);//线程池，保证同一时刻执行的任务能执行s

    @Override
    public void put(MyDelayedEvent delayedEvent) {
        logger.info("插入任务时当前时间是{}",new SimpleDateFormat("HH:mm:ss").format(new Date()));
        queue.put(delayedEvent);
    }

    @Override
    public boolean remove(MyDelayedEvent delayedEvent) {
        logger.info("移除任务");
        return queue.remove(delayedEvent);
    }

    @Override
    public void init() {
        logger.info("初始化学生守护线程");
        //守护线程
        Thread damon = new Thread(this::execute);//新建一个线程，执行execute方法
        damon.setDaemon(false);/*设置为守护线程*/
        damon.setName("Student queue thread");/*线程名*/
        damon.start();
    }

    private void execute() {
        while (true){
            try {
                MyDelayedEvent delayedEvent = queue.take();
                if (delayedEvent!=null){
                    logger.info("执行任务，任务执行时当前时间是{}",TIME_FORMAT.format(delayedEvent.getEndTime()));
                    executor.execute(new Runnable() {
                        //将执行的任务放入线程池，同一个时刻可能有多个任务要执行
                        @Override
                        public void run() {
                            delayedEvent.getTask().executeTask();//执行任务
                        }
                    });
                }
            }catch (InterruptedException e){
                logger.error("任务调度中断");
            }
        }
    }
}
