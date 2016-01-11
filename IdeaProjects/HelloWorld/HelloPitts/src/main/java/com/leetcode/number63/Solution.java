package com.leetcode.number63;

/**
 * Follow up for "Unique Paths":
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * For example,
 * There is one obstacle in the middle of a 3x3 grid as illustrated below.
 * [
 * [0,0,0],
 * [0,1,0],
 * [0,0,0]
 * ]
 * The total number of unique paths is 2.
 * Note: m and n will be at most 100.
 *
 * Created by Peixin Lu on 15/10/13.
 */
public class Solution {

    /**
     * 1代表障碍, 0代表能走
     * 还是DP, 和62题唯一的区别是, 算dp[i][j]的时候, 如果当前位置是障碍, 直接填0
     * 其他都一样!
     * @param obstacleGrid
     * @return
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];

        /**
         * 初始化第一行,这里要注意, 一旦遇到障碍,那么后续全部填0
         */
        boolean meetOb = false;
        for (int i = 0; i < obstacleGrid[0].length; i++) {
            if (meetOb) {
                dp[0][i] = 0;
            } else {
                if (obstacleGrid[0][i] == 1) {
                    dp[0][i] = 0;
                    meetOb = true;
                } else {
                    dp[0][i] = 1;
                }
            }
        }

        /**
         * 初始化第一列, 同上, 遇到障碍,后续全部为0
         */
        meetOb = false;
        for (int i = 0; i < obstacleGrid.length; i++) {
            if (meetOb) {
                dp[i][0] = 0;
            } else {
                if (obstacleGrid[i][0] == 1) {
                    dp[i][0] = 0;
                    meetOb = true;
                } else {
                    dp[i][0] = 1;
                }
            }
        }

        /**
         * 递推和62题唯一区别在于, ij位置如果是障碍,直接填0
         */
        for (int i = 1; i < obstacleGrid.length; i++) {
            for (int j = 1; j < obstacleGrid[0].length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[obstacleGrid.length - 1][obstacleGrid[0].length - 1];
    }

    public static void main (String[] args) {

    }

}
