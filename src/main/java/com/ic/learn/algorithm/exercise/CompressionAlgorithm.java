package com.ic.learn.algorithm.exercise;

public class CompressionAlgorithm {
    public static void main(String[] args) {
        String str = "HG[3|B[2|CA]]F";
        String res = "HGBCACABCACABCACAF";
        System.out.println(solve(str));
    }
    public static String solve(String str){
        if (str==null||str.length()==0){
            return "";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0;i<str.length();i++){
            if (str.charAt(i)=='['){
                int count = 1;
                int num = 0;
                for (int s = i+1;s<str.length();s++){
                    if (str.charAt(s)-'0'<0||str.charAt(s)-'0'>9){
                        String nums = str.substring(i+1,s);
                        for (int m = nums.length()-1;m>=0;m--){
                            num+=(nums.charAt(m)-'0')*Math.pow(10,nums.length()-1-m);
                        }
                        i = s;
                        break;
                    }
                }
                int j = i+1;
                String temp = "";
                for (;j<str.length();j++){
                    if (str.charAt(j)=='['){
                        count++;
                    }
                    if (str.charAt(j)==']'){
                        count--;
                        if (count==0){
                            temp = solve(str.substring(i+1,j));
                            break;
                        }
                    }
                }
                for (int n = 0;n<num;n++){
                    res.append(temp);
                }
                i = j;
            }else{
                res.append(str.charAt(i));
            }
        }
        return res.toString();
    }
}
