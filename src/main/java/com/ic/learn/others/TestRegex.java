package com.ic.learn.others;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
    public static void main(String[] args) {
        String s = "01_03_sfd2ef_10";
//        Pattern p = Pattern.compile("1.*?");
//        Matcher matcher = p.matcher(s);
        s = s.replaceAll("0(\\d+)","$1");
        System.out.println(s);
    }
}
