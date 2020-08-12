package com.ic.learn.algorithm.important.again.researchStatic;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ResearchStatic {
    public static String str = "abc";
    public String s = "fff";
    public ResearchStatic(){

    }
}

class Test{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ResearchStatic rs = new ResearchStatic();
        System.out.println(rs.s);//普通对象可以直接被实例化对象使用
        System.out.println(ResearchStatic.str);//类信息不能直接被实例化对象使用
        Class<?> clazz = rs.getClass();
        Constructor<?> constructor = clazz.getDeclaredConstructor();
        ResearchStatic rs2 = (ResearchStatic) constructor.newInstance();
        System.out.println(rs2.s);
    }
}
