package com.leetcode.number198;

/**
 * You are a professional robber planning to rob houses along a street.
 * Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them
 *  is that adjacent houses have security system connected and it will automatically contact the police if two adjacent
 *   houses were broken into on the same night.
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of
 *  money you can rob tonight without alerting the police.
 *
 *
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * 著名的劫匪题
     * 连续的两个房子不能同时抢.
     * 用DP做
     * dp[i]代表了 前i个房子可抢的最大钱数
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] dp = new int[n];

        /**
         * 初始化
         */
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        /**
         * 递推,有两种可能性:
         * (1)上一次抢的是i-2, 那么i-1不能抢, 所以这种方案是, dp[i-2] + nums[i]
         * (2)上一次抢的是i-1, 那么i不抢了, 这种方案下dp[i] = dp[i-1]
         * 比较一下, 哪个大, dp[i]就是哪个
         */
        for (int i = 2; i < n; i++) {
            int first = dp[i - 2] + nums[i];
            int second = dp[i -1];
            dp[i] = Math.max(first, second);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

    }

}

