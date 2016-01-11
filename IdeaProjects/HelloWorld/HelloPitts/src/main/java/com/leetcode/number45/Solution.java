package com.leetcode.number45;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * DP, 肯定的!
     *
     * @param nums
     * @return
     */
    public static int jump(int[] nums) {
        /**
         * dp[i]代表了跳到第i个位置需要的最少步数
         */
        int[] dp = new int[nums.length];

        /**
         * 初始化
         * dp[0]代表了跳到第0个位置需要的步数,这个是0,因为一开始就站在0位置上
         */
        dp[0] = 0;

        /**
         * 递推公式, i从1到nums.length-1
         * 对每一个位置i, 从0到i挨个找一遍,看看每个位置跳到i位置需要多少步
         * 找到第一个能一步跳到i的位置,步数+1赋给dp[i], 然后break
         */
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] >= i - j) {
                    //能一步跳到i
                    dp[i] = dp[j] + 1;
                    break;//有点贪心的味道
                }
            }
        }

        return dp[nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,4,5,3,1,7};
        System.out.println(jump(nums));

    }
}
