package com.leetcode.number166;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * For example,
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 *
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {

    /**
     * 这题考的是数学!
     * 同时可以认为, 不会出现无限不循环小数
     *
     * @param n
     * @param d
     * @return
     */
    public static String fractionToDecimal(int n, int d) {
        StringBuilder sb = new StringBuilder();
        long numerator = n, denominator = d;
        boolean isNegative = false;
        if (numerator < 0) {
            numerator = -numerator;
            isNegative = !isNegative;
        }
        if (denominator < 0) {
            denominator = -denominator;
            isNegative = !isNegative;
        }

        long quotient = numerator / denominator;
        long remain = numerator - denominator * quotient;


        if (isNegative && numerator !=0)
            sb.append('-');
        sb.append(quotient);
        if (remain != 0) {
            sb.append('.');
            Map<Long, Integer> map = new HashMap<>();
            while (remain != 0 ) {
                //其实就是不断做除法, 直到遇到一个见过的被除数, 那说明开始循环了
                if (!map.containsKey(remain)) {
                    map.put(remain, sb.length());
                    numerator = remain * 10;
                    quotient = numerator / denominator;
                    sb.append(quotient);
                    remain = numerator % denominator;
                } else {
                    sb.insert((int) map.get(remain), '(');//这一步太妙!
                    sb.append(')');
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
//        int a = 1;
//        int b = 6;
//        Double result = (double)a / (double)b;
//        String s = String.valueOf(result);
//        System.out.println(result);
//        System.out.println(s);
//        double d = 2.11232;
//        System.out.println(d + 1);
        long a = 3;
        long b = 7;
        System.out.println(a/b);
    }

}

