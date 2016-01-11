package com.leetcode.number279.PerfectSquare;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
 *
 * Created by Peixin Lu on 16/1/7.
 */
public class Solution {

    /**
     * 动态规划做
     * dp[i]代表n==i的时候, 需要最少的平方数的个数
     * beat 46.25%
     * @param n
     * @return
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            if (i == 1) dp[i] = 1;
            else {
//                int k = i;
                int min = Integer.MAX_VALUE;
                for (int j = 1; j * j <= i; j++) {
                    if (min > dp[i - j * j]) {
                        min = dp[i - j * j];
                    }
                }
                dp[i] = min + 1;
            }
        }
        return dp[n];
    }


    public static void main(String[] args) {
        System.out.println(numSquares(2));
    }
}
