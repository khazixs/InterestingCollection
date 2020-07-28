package com.ic.learn.others;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestScanner {
    /*scanner无法正常在测试函数中使用*/

    public static void main(String[] args) throws FileNotFoundException {
//        Scanner sc = new Scanner(System.in);
        /*在project中，相对路径的根目录是project的根文件夹*/
        String path = new File("src/main/resources/doc/testScanner.txt").getAbsolutePath();/*只是在pathName前加上跟文件夹的绝对路径*/
        System.out.println(path);
        File file = new File(path);/*只能放绝对路径*/
        Scanner sc2 = new Scanner(file);
//        while (sc.hasNextLine()){/*这个判断条件主要是用于读文件时那样就可以生效，在命令行输入时，无法起到结束循环的作用*/
//            String data = sc.nextLine();
//            if (data.equals(""))break;/*这样就可以通过双击enter键进行结束键盘输入*/
//            System.out.println(data);
//        }

        /*=============================================================================================================*/


        /*同样在命令行输入情况下无法结束循环，其正常的结束符应该是有效字符之后的非有效字符如tab enter 空格*/
        /*此处可以调用sc.hasNext的重载方法，自定义结束标识*/
//        while (!sc.hasNext("-1")){
//            String data = sc.next();
//            System.out.println(data);
//        }

        /*=============================================================================================================*/

        /*可以通过输入非数字字符结束循环*/
        while (sc2.hasNextInt()) {
            System.out.println(sc2.nextInt());
        }
//        sc.close();
        sc2.close();
    }
}
