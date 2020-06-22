package com.ic.learn.algorithm.exercise;

public class MyAtoi {
    public static void main(String[] args) {
        String string = "20000000000000000000";
//        System.out.println(string);
//        System.out.println(Integer.MAX_VALUE);
        System.out.println(myAtoi(string));
    }

    public static int myAtoi(String str) {
        if (str==null||str.length()==0){
            return 0;
        }
        int start = 0;
        int end = 1;
        StringBuilder stringBuilder = new StringBuilder();
        boolean isUnusableZero = true;
        while (start < str.length()) {
            if (str.charAt(start) == ' ') {
                start++;
                continue;
            }
            if (str.charAt(start) == '+' || str.charAt(start) == '-' || (str.charAt(start) <= '9' && str.charAt(start) >= '0')) {
                if (str.charAt(start) == '+' || str.charAt(start) == '-' ){
                    if (start+1>=str.length()||str.charAt(start+1)<'0'||str.charAt(start+1)>'9'){
                        return 0;
                    }
                }
                stringBuilder.append(str.charAt(start));
                if (str.charAt(start)<= '9' && str.charAt(start) > '0'){
                    isUnusableZero = false;
                }
                end = start + 1;
                while (end < str.length()) {
                    if ((str.charAt(end) <= '9' && str.charAt(end) >= '0')) {
                        if (str.charAt(end) == '0' && (!isUnusableZero)) {
                            stringBuilder.append(str.charAt(end));
                        }
                        if (str.charAt(end) != '0') {
                            isUnusableZero = false;
                            stringBuilder.append(str.charAt(end));
                        }
                        end++;
                    }else{
                        break;
                    }
                }
                String res = stringBuilder.toString();
                if (res.length()==0||res.equals("+")||res.equals("-")){
                    return 0;
                }
                if (res.length()>11){
                    if (str.charAt(start)=='-'){
                        return Integer.MIN_VALUE;
                    }else{
                        return Integer.MAX_VALUE;
                    }
                }
                long resL = Long.parseLong(res);
                if (resL > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                } else if (resL < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                } else {
                    return (int) resL;
                }
            }else{
                return 0;
            }
        }
        return 0;
    }
}
