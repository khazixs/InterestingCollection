package com.ic.learn.algorithm.exercise;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class LetterCombinations {
    List<String> res = new ArrayList<>();
    String num2 = "abc";
    String num3 = "def";
    String num4 = "ghi";
    String num5 = "jkl";
    String num6 = "mno";
    String num7 = "pqrs";
    String num8 = "tuv";
    String num9 = "wxyz";
    public List<String> letterCombinations(String digits) {
        char[] ds = digits.toCharArray();
        int index = 0;
        StringBuffer sb = new StringBuffer();
        help(ds,sb,0);
        return res;
    }
    public void help(char[] ds,StringBuffer sb,int index){
        if (index>=ds.length){
            String temp = sb.toString();
            res.add(temp);
            return;
        }
        switch (ds[index]){
            case '2':
                for (int i = 0;i<num2.length();i++){
                    sb.append(num2.charAt(i));
                    help(ds,sb,index+1);
                    sb.deleteCharAt(index);
                }
                break;
            case '3':
                for (int i = 0;i<num3.length();i++){
                    sb.append(num3.charAt(i));
                    help(ds,sb,index+1);
                    sb.deleteCharAt(index);
                }
                break;
            case '4':
                for (int i = 0;i<num4.length();i++){
                    sb.append(num4.charAt(i));
                    help(ds,sb,index+1);
                    sb.deleteCharAt(index);
                }
                break;
            case '5':
                for (int i = 0;i<num5.length();i++){
                    sb.append(num5.charAt(i));
                    help(ds,sb,index+1);
                    sb.deleteCharAt(index);
                }
                break;
            case '6':
                for (int i = 0;i<num6.length();i++){
                    sb.append(num6.charAt(i));
                    help(ds,sb,index+1);
                    sb.deleteCharAt(index);
                }
                break;
            case '7':
                for (int i = 0;i<num7.length();i++){
                    sb.append(num7.charAt(i));
                    help(ds,sb,index+1);
                    sb.deleteCharAt(index);
                }
                break;
            case '8':
                for (int i = 0;i<num8.length();i++){
                    sb.append(num8.charAt(i));
                    help(ds,sb,index+1);
                    sb.deleteCharAt(index);
                }
                break;
            case '9':
                for (int i = 0;i<num9.length();i++){
                    sb.append(num9.charAt(i));
                    help(ds,sb,index+1);
                    sb.deleteCharAt(index);
                }
                break;
        }
    }
    @Test
    public void test(){
        String digits = "23";
        List<String> res = letterCombinations(digits);
        System.out.println(res.toString());
    }

}
