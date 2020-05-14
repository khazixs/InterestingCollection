package com.ic.learn.algorithm.exercise;

import com.ic.userManagement.entity.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectPoolFactory {
    /*对象池，<对象名，实际对象>*/
    private Map<String, Object> objectPool = new HashMap<>();

    /*创建对象的方法，只需要传入一个对象名的字符串，程序自动生成一个该类的对象*/
    private Object createObject(String clazzName) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*获取对象名所属的类对象*/
        Class<?> clazz = Class.forName(clazzName);
        /*使用clazz对应类的默认构造器创建对象实例*/
        /*jdk9之后就不推荐直接使用Class对象的newInstance,而是先获取构造器再调用构造器的newInstance；
         *getDeclaredConstructor()获取所有权限的构造器包括私有的和非私有的，有参数过滤；getDeclaredConstructors()获取所有构造器，无参数类型过滤*/
        /*getConstructor()获取public修饰的构造器，有参数类型过滤；getConstructors()无参数类型过滤*/
        return clazz.getDeclaredConstructor().newInstance();
    }

    /*根据指定文件初始化对象池，然后根据指定文件来创建对象*/
    public void initPool(String fileName){
        try {
            FileInputStream fin = new FileInputStream(fileName);
            Properties properties = new Properties();
            properties.load(fin);
            for (String name : properties.stringPropertyNames()){
                /*没取出一个key-value就创建一个对象,key是对象名，value是对象类的全限定类名*/
                objectPool.put(name,createObject(properties.getProperty(name)));
            }
        } catch (IOException | InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Object getObject(String name){
        return objectPool.get(name);
    }

    public static void main(String[] args) {
        ObjectPoolFactory objectPoolFactory = new ObjectPoolFactory();
        objectPoolFactory.initPool("F:\\project\\InterestingCollection\\src\\main\\resources\\properties\\testReflect.properties");
        System.out.println(objectPoolFactory.getObject("a"));
        System.out.println(objectPoolFactory.getObject("b"));
    }
}
