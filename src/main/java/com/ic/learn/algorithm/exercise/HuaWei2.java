package com.ic.learn.algorithm.exercise;

import com.ic.learn.algorithm.exercise.LetterCombinations;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HuaWei2 {
    static int diff = Integer.MAX_VALUE;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("E:\\project\\InterestingCollection\\src\\main\\java\\com\\ic\\learn\\others\\huawei.txt"));
        String[] data = sc.nextLine().split(";");
        List<String> target = cut(data[0]);
        List<String> mode = cut(data[1]);
        int res2 = mode.size();
        System.out.println("mode:" + mode.toString());
        System.out.println("target:" + target.toString());
        help(target, mode, 0, 0, 0);
        System.out.println("(" + diff + "," + res2 + ")");
    }

    public static void help(List<String> target, List<String> mode, int it, int im, int count) {
        if (it >= target.size() || im >= mode.size()) {
            if (it >= target.size()) {
                count += (mode.size() - im);
            } else {
                count += (target.size() - it);
            }
            int size = Math.min(target.size(), mode.size());
            for (int i = 0; i < size; i++) {
                if (!target.get(i).equals(mode.get(i))) {
                    return;
                }
            }
            diff = Math.min(count, diff);
            return;
        }

        String t = target.get(it);
        String m = mode.get(im);
        if (!t.equals(m)) {
            /*靠增加*/
            target.add(it, m);
            help(new LinkedList<>(target), mode, it + 1, im + 1, count + 1);
            /*靠修改*/
            target.remove(it + 1);
            help(new LinkedList<>(target), mode, it + 1, im + 1, count + 2);
            /*靠删除*/
            target.remove(it);
            help(new LinkedList<>(target), mode, it, im, count + 1);
        } else {
            help(new LinkedList<>(target), mode, it + 1, im + 1, count);
        }
    }

    public static List<String> cut(String data) {
        String[] source = data.split(" ");
        List<String> res = new LinkedList<>();
        for (String temp : source) {
            if (temp.equals(" ")) {
                continue;
            }
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < temp.length(); j++) {
                if (temp.charAt(j) == ',' || temp.charAt(j) == '.' || temp.charAt(j) == '!') {
                    if (sb.length() != 0) {
                        res.add(sb.toString().toLowerCase());
                    }
                    res.add(".");
                    sb = new StringBuffer();
                } else if (temp.charAt(j) == '?') {
                    if (sb.length() != 0) {
                        res.add(sb.toString().toLowerCase());
                    }
                    res.add("?");
                    sb = new StringBuffer();
                } else {
                    sb.append(temp.charAt(j));
                }
            }
            if (sb.length() != 0) {
                res.add(sb.toString().toLowerCase());
            }
        }
        return res;
    }

    @Test
    public void test() {
        String s = "A , c?d.j";
        List<String> list = cut(s);
        System.out.println(list.toString());
    }

}
