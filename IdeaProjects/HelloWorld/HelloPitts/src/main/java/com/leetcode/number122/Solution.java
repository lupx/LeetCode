package com.leetcode.number122;

/**
 * Best Time to Buy and Sell Stock II:
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times).
 * However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * 类似于前一题
     * 只不过,这个题把所有的正数prices[i] - prices[i + 1]加起来即可
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int profit = 0;
        int thisSum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int tmp = prices[i + 1] - prices[i];
            if (tmp > 0) {
                profit += tmp;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,2,1,4,7,2,4,1}));
    }
}

