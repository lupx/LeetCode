package com.leetcode.number171;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28
 *
 * Created by Peixin Lu on 15/11/27.
 */
public class Solution {


    /**
     * 标题转数字
     * 81.73%
     * @param s
     * @return
     */
    public static int titleToNumber(String s) {
        char[] sc = s.toCharArray();
        int sum = 0;
        int exp = 1;
        for (int i = sc.length - 1; i >= 0; i--) {
            sum += (sc[i] - 'A' + 1) * exp;
            exp *= 26;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(titleToNumber("CEQD"));
    }

}

