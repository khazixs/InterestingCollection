package com.ic.learn.entity;

<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

=======
>>>>>>> 024cd5ca7fdd0b2d2db9688cfa42ca8ef817b877
public class Commodity {
    Integer id;
    String name;
    Integer value;
<<<<<<< HEAD
    @Autowired
    static RedisTemplate redisTemplate = new RedisTemplate();
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader;
        Commodity commodity = new Commodity();
        //classLoader  = Commodity.class.getClassLoader();
        classLoader = Class.forName("com.ic.learn.entity.Commodity").getClassLoader();
        classLoader = commodity.getClass().getClassLoader();
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        threadPool.execute(() -> {
            for (int i = 0;i<10;i++){
                System.out.println(123);
            }
        });
//        redisTemplate.opsForZSet().intersectAndStore()
    }
=======
>>>>>>> 024cd5ca7fdd0b2d2db9688cfa42ca8ef817b877
}
