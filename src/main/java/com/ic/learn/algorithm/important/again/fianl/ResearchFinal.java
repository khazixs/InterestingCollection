package com.ic.learn.algorithm.important.again.fianl;

public class ResearchFinal {
    static final StringBuilder str = new StringBuilder("abc");

    public static void main(String[] args) {
//        str = new StringBuilder();//不能引用其他对象
    }

    public final static void print() {

    }
}

class Test extends ResearchFinal {
    /*不能覆写*/
//    public final static void print(){
//
//    }
    public static void main(String[] args) {

    }
}
