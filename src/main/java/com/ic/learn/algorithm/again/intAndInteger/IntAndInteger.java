package com.ic.learn.algorithm.again.intAndInteger;

import org.junit.Test;

public class IntAndInteger {
    @Test
    public void test1(){
        int i = 10;
        Integer ii = new Integer(10);
        //int 与 integer 进行比较的时候，会发生自动拆箱，直接比较的是数字的大小
        System.out.println(i==ii);
    }

    @Test
    public void test2(){
        Integer i = new Integer(10);

        Integer i1 = new Integer(10);

        System.out.println(i == i1);//这里比较的是两个Integer对象的引用故不相等
        System.out.println(i.equals(i1));//这里比较的是值
    }

    @Test
    public void test3(){
        /*其实在创建Integer时大小在-128到127之间的数字是保存在常量池的，这里直接给Integer类型的对象赋int值 是自动装箱的过程*/
        Integer i1 = 10;//常量池
        Integer i2 = 10;//常量池，故引用的是一个对象
        Integer i3 = 128;//新建对象
        Integer i4 = 128;//新建对象，故引用肯定不同

        System.out.println(i1 == i2);
        System.out.println(i3 == i4);
    }
}
