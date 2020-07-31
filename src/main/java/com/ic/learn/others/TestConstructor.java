package com.ic.learn.others;

class A {

    static {
        System.out.print("1");
    }

    public A() {
        System.out.print("2");
    }
}

class B extends A {

    static {
        System.out.print("a");
    }

    public B() {
        System.out.print("b");
    }
}

/*创建对象时构造器的调用顺序是：先初始化静态成员（先父类再子类），然后调用父类构造器，再初始化非静态成员，最后调用自身构造器。*/
public class TestConstructor {

    public static void main(String[] args) {
        A ab = new B();
        System.out.print("\n");
        /*静态成员只会初始化一次*/
        B b = new B();
    }

}

