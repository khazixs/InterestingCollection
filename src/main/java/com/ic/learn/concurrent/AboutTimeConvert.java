package com.ic.learn.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class AboutTimeConvert {
    public static void main(String[] args) throws InterruptedException {
        long nt = System.currentTimeMillis();//直接这样获取的是Unix时间,Unix时间是long类型
        System.out.println(nt);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(nt)));//Unix时间转Date
        TimeUnit.SECONDS.sleep(2);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));//new Date()等同于System.currentTimeMillis()，获取的结果时间意义上是一致的
        System.out.println(new Date().getTime());//Date转Unix时间
    }
}
