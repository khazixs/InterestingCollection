package com.ic.learn.algorithm.exercise;

import org.junit.Test;

public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        boolean res = false;
        int len = s.length();
        for(int i = 1;i<=len/2;i++){
            if(len%i==0){
                String target = s.substring(0,i);
                res=true;
                for (int j = i;j<=len-i;j+=i){
                    String source = s.substring(j,j+i);
                    if (!source.equals(target)){
                        res=false;
                        break;
                    }
                }
                if (res){
                    return true;
                }
            }
        }
        return false;
    }

    @Test
    public void test(){
        String s = "a";
        System.out.println(repeatedSubstringPattern(s));
    }
}
