package com.ic.learn.algorithm.exercise;

public class FindNumsAppearOnce {
    public static void main(String[] args) {
        System.out.println(getBit((byte) 3,0));
        System.out.println(getBits((byte)6,0,3));
    }

    //b为传入的字节，i为第几位（范围0-7），如要获取bit0，则i=0
    public static int getBit(byte b,int i) {
        return (b>>i) & 0x1;
    }


    //b为传入的字节，start是起始位，length是长度，如要获取bit0-bit4的值，则start为0，length为5
    public static String getBits(byte b,int start,int length) {
        return Integer.toBinaryString ((b>>start)&(0xFF>>(8-length)));
    }
}
