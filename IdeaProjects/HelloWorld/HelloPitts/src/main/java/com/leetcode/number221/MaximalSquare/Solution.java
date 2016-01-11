package com.leetcode.number221.MaximalSquare;

/**
 * 在二维数组char[][]中,找到最大正方形, 其全部由'1'组成
 * Created by Peixin Lu on 15/12/28.
 */
public class Solution {

    /**
     * 二维数组动态规划题
     * 定义dp函数 int[][] dp
     * dp[i][j]表示了 从左上角到matrix[i][j]能够包含的最大正方形的面积是多少
     * beat 83.22%
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int N = matrix.length;
        int M = matrix[0].length;
        int[][] dp = new int[N][M];
        int max = 0; //max edge length of the Maximal Square;

        /**
         * 初始化第一行
         */
        for (int j = 0; j < M; j++) {
            dp[0][j] = matrix[0][j] - '0';
            if (dp[0][j] > max)
                max = dp[0][j];
        }

        /**
         * 初始化第一列
         */
        for (int i = 0; i < N; i++) {
            dp[i][0] = matrix[i][0] - '0';
            if (dp[i][0] > max)
                max = dp[i][0];
        }

        for (int i = 1; i < N; i++)
            for (int j = 1; j < M; j++) {
                if (matrix[i][j] == '0')
                    dp[i][j] = 0;
                else {
                    dp[i][j] = getMin(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                    if (dp[i][j] > max)
                        max = dp[i][j];
                }
            }
        return max * max;
    }

    private static int getMin(int a, int b, int c) {
        int min = Integer.min(a, b);
        return Integer.min(min, c);
    }

    public static void main(String[] args) {
    }
}
