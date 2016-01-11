package com.leetcode.number168;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * For example:
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {

    /**
     * 这个解法实在太笨了!!!
     * @param n
     * @return
     */
    public static String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        if (n <= 0) return "";
        int power = 0;
        int divider = 1;
        int q = 1;
        int remainder = 1;
        int lower = 1;
        while (n != 0) {
            if (n <= 26) {
                sb.append((char)(65 + n - 1));
                break;
            } else {
                power = powerOf26(n);
                divider = 1;
                for (int i = 0; i < power; i++) {
                    divider *= 26;
                }
                q = n / divider;
                remainder = n - q * divider;
                lower = 1;
                power--;
                if (power == 0) lower = 0;
                else {
                    for (int i = 0; i < power; i++) {
                        lower *= 26;
                    }
                }
                if (remainder <= lower) {
                    //需借位
                    //高位-1
                    if (q == 1) {
                        sb.append("Z");
                    } else {
                        sb.append((char) (65 + q - 2));
                        remainder += divider;
                    }
                } else {
                    //无需借位
                    sb.append((char) (65 + q - 1));
                }
                n = remainder;
            }
        }
        return sb.toString();
    }

    private static int powerOf26(int n) {
        int power = 0;
        while (n > 26) {
            n /= 26;
            power++;
        }
        return power;
    }


    /**
     *
     * @param n
     * @return
     */
    public static String convertToTitle_v2(int n) {
        StringBuilder result = new StringBuilder();
        while(n > 0){
            int temp = n % 26 == 0 ? 26 : n % 26;
            result.append((char)('A' + n % 26));
            n = (n - temp) / 26;
        }
        result.reverse();
        return result.toString();
    }

    public static void main(String[] args) {
//        System.out.println(powerOf26(52));
        System.out.println(convertToTitle_v2(38275));
    }

}

