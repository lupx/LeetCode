package com.leetcode.number263.UglyNumber;

/**
 * 寻找丑数:
 * 仅由2/3/5三个质数作为因子组成的数就是丑数, 比如, 6和8都是丑数, 而14不是, 因为14有7作为因子.
 * 此外, 约定1为丑数
 * Created by Peixin Lu on 16/1/3.
 */
public class Solution {

    /**
     *
     * @param num
     * @return
     */
    public static boolean isUgly(int num) {
        if (num <= 0) return false;
        if (num == 1) return true;
        while (num != 1) {
            if (num % 2 == 0) {
                num /= 2;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if (num % 5 == 0) {
                num /= 5;
            } else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUgly(4));
    }
}