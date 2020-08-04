package com.ic.learn.algorithm.important.again.aboutRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UseRegex {
    public static void main(String[] args) {
        String str = "(成都市)(成华区)(武侯区)(高新区)";
        Pattern p = Pattern.compile("\\((.*?)\\)");//模板对象
        Matcher m = p.matcher(str);//匹配结果对象
        /*部分匹配并分组*/
        while (m.find()) {
            System.out.println(m.group(1));
        }
        System.out.println(m.matches());//表示全匹配
    }

}
