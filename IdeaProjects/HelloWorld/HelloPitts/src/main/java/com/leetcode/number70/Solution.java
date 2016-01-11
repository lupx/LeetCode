package com.leetcode.number70;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * Created by Peixin Lu on 15/10/14.
 */
public class Solution {

    /**
     * 每次往上爬1或2步.
     * 用DP解法
     * dp[i]代表爬到第i层有几种爬法
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n <= 0) return 1;
        if (n == 1) return 1;
        /**
         * dp[i]代表爬到第i+1层可能有的方法数
         */
        int[] dp = new int[n];

        /**
         * 初始化
         */
        dp[0] = 1;
        dp[1] = 2;//数组i对应i+1层

        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }


    public static void main (String[] args) {
        System.out.println(climbStairs(5));
    }

}
