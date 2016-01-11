package com.leetcode.number62;

/**
 *
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 *
 * Created by Peixin Lu on 15/10/13.
 */
public class Solution {

    /**
     * 一看矩阵,而且还是求总方案数, 基本可以确定是用DP来做了
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {

        /**
         * dp[i][j]代表走到这个位置共有几条路径
         */
        int[][] dp = new int[m][n];

        /**
         * 初始化第一行, 每一个显然都是1
         */
        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = 1;
        }

        /**
         * 初始化第一列, 每一个显然都是1
         */
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }

        /**
         * 递推公式:
         * 由于每一格要么是从上面走下来,要么是从左边走过来
         * 所以, dp[i][j] = dp[i-1][j] + dp[i][j-1]
         */
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main (String[] args) {

    }

}
