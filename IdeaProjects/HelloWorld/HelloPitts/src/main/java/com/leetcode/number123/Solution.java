package com.leetcode.number123;

/**
 * Best Time to Buy and Sell Stock III:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most two transactions.
 * 只做两次交易,找到最大盈利
 *
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * 找到最大的两笔盈利!
     * 再转换, 找到两个最大子序列和
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length;
        int profit = 0;

        // scan from left
        // left[i] keeps the max profit from 0 to i
        int[] left = new int[n];
        int min = prices[0];

        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        // scan from right
        // right[i] keeps the max profit from i to n - 1
        int[] right = new int[n];
        int max = prices[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);

            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;

    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,2,1,6,4,2,3}));
//        [1, 2]
    }
}

