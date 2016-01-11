package com.leetcode.number121;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock),
 * design an algorithm to find the maximum profit.
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * DP!
     * 其实还是用maximum subarray的思想
     *
     * [198 testcases, 2ms, beat 60%]
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int profit = 0;
        int thisSum = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            thisSum += -1 * (prices[i] - prices[i + 1]);
            if (thisSum < 0) {
                thisSum = 0;
            } else {
                if (thisSum > profit) profit = thisSum;
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,2,1}));
    }
}

