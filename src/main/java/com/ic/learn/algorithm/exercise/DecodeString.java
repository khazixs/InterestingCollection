package com.ic.learn.algorithm.exercise;

public class DecodeString {
    public static void main(String[] args) {
        String s = "100[a]";
        System.out.println(decodeString(s));
    }

    public static String decodeString(String s) {
        if (s == null || s.equals("")) {
            return "";
        }
        int len = s.length();
        int index = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (index < len) {
            if (s.charAt(index) - '0' >= 0 && '9' - s.charAt(index) >= 0) {
                int numEnd = getNumEnd(s,index);
                int count = Integer.parseInt(s.substring(index,numEnd));
                index = numEnd;
                int start = index;
                int end = getEnd(s, start);
                if (end == -1) {
                    return "";
                }
                String str = decodeString(s.substring(start+1, end));
                while (count > 0) {
                    stringBuilder.append(str);
                    count--;
                }
                index = end + 1;
            } else {
                stringBuilder.append(s.charAt(index));
                index++;
            }
        }
        return stringBuilder.toString();
    }
    private static int getNumEnd(String s, int index){
        if (s == null||s.equals("")){
            return 0;
        }
        while (index<s.length()){
            if (s.charAt(index) - '0' >= 0 && '9' - s.charAt(index) >= 0){
                index++;
            }else{
                break;
            }
        }
        return index;
    }
    private static int getEnd(String s, int start) {
        int left = 0;
        int i = start;
        while (i < s.length()) {
            if (s.charAt(i) == '[') {
                left++;
            } else if (s.charAt(i) == ']') {
                left--;
                if (left == 0) {
                    return i;
                }
            }
            i++;
        }
        return -1;
    }
}
