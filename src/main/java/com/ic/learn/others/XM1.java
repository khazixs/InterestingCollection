package com.ic.learn.others;
import java.util.*;
public class XM1 {
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            String[] data = sc.nextLine().split(" ");
            for(int i = 0;i<data.length;i++){
                System.out.println(help(data[i]));
            }
        }

        public static int help(String str){
            if(str==null||str.length()<8||str.length()>120){
                return 1;
            }

            char[] data = str.toCharArray();
            int[] type = new int[4];
            for(char c : data){
                if(type[0]==0&&c-'0'>=0&&c-'0'<=9){
                    type[0]=1;
                    continue;
                }
                if(type[2]==0&&c>=65&&c<=90){
                    type[2]=1;
                    continue;
                }
                if(type[3]==0&&c>=97&&c-'0'<=122){
                    type[3]=1;
                    continue;
                }
                type[1] = 1;
            }
            for(int i : type){
                if(i==0){
                    return 2;
                }
            }
            return 0;
        }
}
