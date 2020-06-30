package com.ic.learn.entity;

public interface TestInterface {
    default void print(){
        System.out.println(1.2);
    }

    public static void main(String[] args) {
        
    }
}
