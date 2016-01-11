package com.leetcode.number120;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.
 * For example, given the following triangle
    [
    [2],
    [3,4],
    [6,5,7],
    [4,1,8,3]
    ]
    The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 * Note:
 * Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 * 如果只用O(n)空间的话,就更好!
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * DP
     * dp[i]表示每一层得到各个方案的子sum, 参与和下一层的计算, 并且更新进dp[]
     * 最后在dp[i]中找最小值, 即为所求
     * 6-7ms, 比较慢..why??
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) return 0;
        int[] dp = new int[triangle.size()];
        List<Integer> tmp;
        /**
         * 初始化
         */
        dp[0] = 0;

        /**
         * 递推
         */
        int min = 0;
        for (int i = 0; i < triangle.size(); i++) {
            tmp = triangle.get(i);
            min = Integer.MAX_VALUE;
            for (int j = tmp.size() - 1; j >= 0; j--) {
                if (j == 0) dp[j] = dp[j] + tmp.get(j);
                else if (j < tmp.size() - 1){
                    dp[j] = Math.min(dp[j - 1] + tmp.get(j), dp[j] + tmp.get(j));
                } else {
                    dp[j] = dp[j - 1] + tmp.get(j);
                }
                if (dp[j] < min) min = dp[j];
            }
        }
        return min;
    }

    public static void main(String[] args) {
    }
}

