package com.leetcode.number172;

/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 * Created by Peixin Lu on 15/11/27.
 */
public class Solution {

    /**
     * 找5和10的个数.
     * O(n)时间, 太慢, 题目要求log时间内完成
     * 那只能考虑用二分法!
     * @param n
     * @return
     */
    public static int trailingZeroes(int n) {
        int count = 0;
        long res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
            if (res % 10 == 0) {
                count++;
                if (res >= 10) {
                    //更新res
                    res /= 10;
                }
            } else {
                if (res >= 10) {
                    res %= 10;
                }
            }
        }
        while (res % 10 == 0) {
            count++;
            res /= 10;
        }
        return count;
    }

    /**
     * log(n)时间完成!
     * 只能用二分法!
     * 2ms
     * @param n
     * @return
     */
    public static int trailingZeroesLog(int n) {
        int count = 0;
        while(n > 0){
            n = n / 5;
            count += n;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroesLog(200));
    }

}

