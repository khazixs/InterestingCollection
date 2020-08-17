package com.ic.learn.others;

public class addString {
    public static void main(String[] args) {
        System.out.println(addStrings("999","0"));
    }

    public static String addStrings (String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        int one = 0;
        int index1 = len1-1,index2 = len2-1;
        StringBuffer res = new StringBuffer();
        while (index1>=0&&index2>=0){
            int num = (num1.charAt(index1)-'0')+(num2.charAt(index2)-'0')+one;
            if (num>=10){
                res.append(num-10);
                one = 1;
            }else{
                res.append(num);
                one = 0;
            }
            index1--;
            index2--;
        }
        while (index1>=0){
            int num = num1.charAt(index1--)-'0'+one;
            if (num>=10){
                res.append(num-10);
                one = 1;
            }else{
                res.append(num);
                one = 0;
            }
        }
        while (index2>=0){
            int num = num2.charAt(index2--)-'0'+one;
            if (num>=10){
                res.append(num-10);
                one = 1;
            }else{
                res.append(num);
                one = 0;
            }
        }
        if (one==1){
            res.append(1);
        }
        res.reverse();

        return res.toString();
    }
}
