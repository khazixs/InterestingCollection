package com.ic.learn.algorithm.exercise;

public class NumOf1Between1AndN {
    public static void main(String[] args) {
        System.out.print(solution1(2104));
    }

    public static int solution1(int n) {
        int count = 0;
        int i = 1;
        int current = 0, low = 0, high = 0;
        while ((n / i) != 0) {
            high = n / (i * 10);
            current = (n / i) % 10;
            low = n - (n / i) * i;
            /*如果为0，出现1的次数由高位决定，以2104为例当看10位时候，共出现过210次，
            * 因为0010~0019,0110~0119,···,2010~2019共210组
            * */
            if (current == 0) {
                count = count + high * i;
            } else if (current == 1) {
                /*如果为1，取决于高位和低位，即以2104为例
                * 有0100~0199,1100~1199两组各100个，再加上2100~2104这个区间的5个共205个*/
                count = count + high * i + low + 1;
            } else if (current > 1) {
                /*如果大于1则取决于高位，以2104为例
                * 千位时，有01000~01999这个区间1000个，个位数时：有0001~2101共211个数，总计1211个*/
                count = count + (high + 1) * i;
            }
            i = i * 10;
        }
        /*总计：210+205+1211 = 1626个*/
        return count;
    }

    public static int numberOf1(int n) {
        int count = 0;
        for (int i = 1; i < n; i *= 10) {
            int a = n / i;/*高位*/
            int b = n % i;/*低位*/
            count += (a + 8) / 10 * i;
            if (a % 10 == 1) {
                count += b + 1;
            }
        }
        return count;
    }
}
