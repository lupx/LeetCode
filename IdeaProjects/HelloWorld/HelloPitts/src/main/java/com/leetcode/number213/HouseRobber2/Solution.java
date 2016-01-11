package com.leetcode.number213.HouseRobber2;

/**
 * Note: This is an extension of House Robber.
 * After robbing those houses on that street, the thief has found himself a new place for his thievery so that
    he will not get too much attention. This time, all houses at this place are arranged in a circle.
 * That means the first house is the neighbor of the last one.
 * Meanwhile, the security system for these houses remain the same as for those in the previous street.
 * Given a list of non-negative integers representing the amount of money of each house,
 * determine the maximum amount of money you can rob tonight without alerting the police.
 *  Created by Peixin Lu on 15/12/26.
 */
public class Solution {

    /**
     * 唯一的差别在于, 要考虑第一间房子偷的同时, 最后一个房子不能偷.  若要偷最后一个房子, 那么第一个房子一定不能偷
     * 这就产生了两个方案最终的比拼问题.
     * (1)第一个房子肯定偷, 最后一个房子肯定不偷, 算最终最大利益 profit1
     * (2)第一个房子肯定不偷, 最后一个房子不一定偷, 算最终最大利益 profit2
     * 两个部分都是HouseRobberI的算法.
     * 这个变换想清楚, 就很简单了
     * return Math.max(profit1, profit2);
     * beat 6.39%
     * 所以, 实际上是两个不同的dp过程
     * @param nums
     * @return
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;
        if (N == 1) return nums[0];
        if (N == 2) return Math.max(nums[0], nums[1]);
        if (N == 3) return Math.max(nums[0], Math.max(nums[1], nums[2]));


        int profit1, profit2;

        //dp函数, 此题可以理解为:
        // 方案一, 第一间房必偷, 最后一间房不偷, 实际上就是从第三间房到倒数第二间房子的最大利益 + 第一间房利益
        // 方案二, 第一间房必不偷, 最后一间房不一定偷, 实际上就是从第二间房开始到最后一间房的最大利益
        // 方案一, 可以考虑的房子总数是nums.length - 3, 这部分可以用HouseRobberI的算法来解
        // 方案二, 可以考虑的房子总数是nums.length - 1, 这部分实际上是从第二间房到倒数第二间房之间的HouseRobberI
        int[] dp;

        //case 1: 第一个房子肯定偷
        //初始化, 等价于从第三个房子开始到倒数第二个房子之间的最大利益 + 第一个房子的利益
        dp = new int[N - 3];
        dp[0] = nums[2];
        if (dp.length >= 2) {
            dp[1] = Math.max(dp[0], nums[3]);
            /**
             * 递推
             */
            for (int i = 2; i < N - 3; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i + 2], dp[i - 1]);
            }
        }
        profit1 = dp[N - 4] + nums[0];//加上第一间房抢到的, 得到方案一的最大利益

        //case 2: 注意最后一间房不一定偷
        //初始化, 实际等价于从第二个房子开始到最后一间房子之间的最大利益
        dp = new int[N - 1];
        dp[0] = nums[1];
        dp[1] = Math.max(dp[0], nums[2]);
        /**
         * 递推
         */
        for (int i = 2; i < N - 1; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i + 1], dp[i - 1]);
        }
        profit2 = dp[N - 2];

        return Math.max(profit1, profit2);
    }


    public static void main(String[] args) {
        System.out.println(rob(new int[]{2,7,7,7,4}));
    }
}
