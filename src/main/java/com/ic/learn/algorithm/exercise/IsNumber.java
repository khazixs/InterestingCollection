package com.ic.learn.algorithm.exercise;

import org.junit.Test;

public class IsNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        int length = s.length();
        if (s==null||length==0){
            return false;
        }
        int index = 0;
        boolean pointExist = false;
        if (s.charAt(index)=='e'||s.charAt(index)=='E'||s.charAt(index)=='.'){
            return false;
        }
        if (s.charAt(index) == '+' || s.charAt(index) == '-') {
            index++;
            if (index>=length){
                return false;
            }
            if (s.charAt(index) - '0' < 1 || s.charAt(index) - '0' > 9) {
                return false;
            }
        }

        while (index<length){
            if (s.charAt(index)=='E'||s.charAt(index)=='e'){
                return isNumber(s.substring(index + 1, length));
            }
            if (s.charAt(index)=='.'){
                if (pointExist){
                    return false;
                }else{
                    pointExist = true;
                    if (++index < length){
                        if (s.charAt(index)-'0'<=9||s.charAt(index)-'0'>=0){
                            index++;
                            continue;
                        }else{
                            return false;
                        }
                    }
                }
            }
            if (s.charAt(index) - '0'<0||s.charAt(index) -'0'>9){
                return false;
            }
            index++;
        }
        return true;
    }

    @Test
    public void test() {
        String s = "1 ";
        System.out.println(isNumber(s));
    }
}
