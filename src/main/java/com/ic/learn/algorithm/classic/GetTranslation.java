package com.ic.learn.algorithm.classic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetTranslation {
    public static void main(String[] args) {
        System.out.println(getTranslationCount(12258));
    }

    private static int getTranslationCount(Integer source) {
        if (source < 0) {
            return 0;
        }
        String numberInString = String.valueOf(source);
        return helper(numberInString);
    }

    private static int helper(String number) {
        int length = number.length();
        int[] counts = new int[length];/*记录子问题能翻译的个数*/
        int count = 0;

        for (int i = length - 1; i >= 0; i--) {
            if (i < length - 1) {
                count = counts[i + 1];
            } else {
                count = 1;
            }

            if (i < length - 1) {
                int digit1 = number.charAt(i) - '0';
                int digit2 = number.charAt(i + 1) - '0';
                int converted = digit1 * 10 + digit2;
                if (converted >= 10 && converted <= 25) {
                    if (i < length - 2) {
                        count += counts[i + 2];
                    } else {
                        count += 1;
                    }
                }
            }
            counts[i] = count;
        }
        count = counts[0];
        return count;
    }
}
