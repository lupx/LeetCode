package com.leetcode.number188;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * DP解法一:
     * dp[k+1][n]:  dp[i][j]表示用最多i次交易, 走到第j天,能达到的最大profit
     * 速度很快! beat 84.26%!!!
     * 复杂度: 时间空间都是O(nk).
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitV1(int k, int[] prices) {
        if (prices == null) return 0;
        int n = prices.length;
        if (n <= 1) return 0;

        /**
         * 首先, 考虑特殊情况
         * k >= n/2, 此时想做多少次交易都可以
         * 所以一次遍历算最大profit返回即可
         */
        if(k >= prices.length / 2){
            int maxProfit = 0;
            for(int i = 1; i < prices.length; i++){
                if(prices[i] > prices[i - 1]) maxProfit += prices[i] - prices[i - 1];
            }
            return maxProfit;
        }

        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            /**
             * localMax : 前j-1天内最多i-1次交易所产生的最大profit
             * 所以, 根据定义, dp[i][j]应该在两个值间作取舍:
             *   (1)前j-1天里已经完成了i次交易的最大profit
             *   (2)前j-1天完成了i-1次交易, 同时第j天发生一次交易的最大profit
             *   显然, (2)的profit由两个部分相加组成:
             *       1. 前j-1天发生i-1次交易的最大profit
             *       2. 第j天发生第i次交易的profit
             *
             * 所以显然, localMax初始值为: 截止第0天发生i-1次交易的profit(肯定是0) - prices[0](第0天交易额)
             * 并且,每次循环都要更新localMax.
             */
            int localMax = dp[i - 1][0] - prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1],  prices[j] + localMax);
                localMax = Math.max(localMax, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }


    /**
     * DP解法二:
     * 不太好理解
     * beat 64.77%, 比上面稍慢一点点
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfitV2(int k, int[] prices) {
        /**
         * 和上面同理
         */
        if (k >= prices.length / 2) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) maxProfit += prices[i] - prices[i - 1];
            }
            return maxProfit;
        }

        int[] maxProfit = new int[k + 1];//maxProfit[i]代表最多i次交易的最大profit
        int[] lowPrice = new int[k + 1];//lowPrice[i]代表最多i次交易的最低价格??

        for (int i = 0; i < lowPrice.length; i++) lowPrice[i] = Integer.MAX_VALUE;
        for (int p : prices) {
            for (int i = k; i >= 1; i--) {
                maxProfit[i] = Math.max(maxProfit[i], p - lowPrice[i]);
                lowPrice[i] = Math.min(lowPrice[i], p - maxProfit[i - 1]);
            }
        }
        return maxProfit[k];
    }

    public static void main(String[] args) {
    }

}

