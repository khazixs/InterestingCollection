package com.ic.learn.others;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwentyFourGame {
    public static boolean judgePoint24(int[] nums) {
        //转成double计算，丢坑里了
        //[8,1,6,6] 6÷(1-6÷8) =6÷(1-6/8) =6÷2/8 =6x8/2 =6x4 =24
        double[] doubles = Arrays.stream(nums).asDoubleStream().toArray();
        return judgePoint24(doubles);
    }

    public static boolean judgePoint24(double[] nums) {
        //递归出口，剩余一个数，判断是不是24点
        if (nums.length == 1) {
            //处理计算精度问题
            return nums[0] > 23.999 && nums[0] < 24.001;
        }

        //任意拿两个不相同的数，通过运算符组成第三个数，进行后续的24点计算
        //其中，括号不用考虑，因为是任意两个数的所有允许符都参与，所以有括号，没括号的情况，都包含在内了
        for (int x = 0; x < nums.length - 1; x++) {
            for (int y = x + 1; y < nums.length; y++) {
                //是否合法
                boolean isValid = false;

                //每次两数操作 出来的数组是原来数据的length-1
                //删除后面的元素（这样不会影响前面的元素），前面的元素用来放置新值
                double[] temp = new double[nums.length - 1];
                //copy待删除元素y的前部
                System.arraycopy(nums, 0, temp, 0, y);
                //copy待删除元素y的后部
                System.arraycopy(nums, y + 1, temp, y, temp.length - y);

                //加法
                temp[x] = nums[x] + nums[y];
                isValid = isValid || judgePoint24(temp);

                //减法（减与被减）
                temp[x] = nums[x] - nums[y];
                isValid = isValid || judgePoint24(temp);
                temp[x] = nums[y] - nums[x];
                isValid = isValid || judgePoint24(temp);

                //乘法
                temp[x] = nums[x] * nums[y];
                isValid = isValid || judgePoint24(temp);

                //除法，除与被除
                if (nums[y] != 0) {
                    temp[x] = nums[x] / nums[y];
                    isValid = isValid || judgePoint24(temp);
                }
                if (nums[x] != 0) {
                    temp[x] = nums[y] / nums[x];
                    isValid = isValid || judgePoint24(temp);
                }

                //合法就返回
                if (isValid) {
                    return true;
                }
            }
        }
        //不合法
        return false;
    }
}
