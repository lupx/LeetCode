package com.leetcode.number91;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 * Created by PeixinLu on 15/10/21.
 */
public class Solution {

    /**
     * 明显拿DP做!
     *
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        if (s.charAt(0) == '0') return 0;
        int len = s.length();

        /**
         * DP数组, dp[i]代表了s前i个字符能decode的方法数
         */
        int[] dp = new int[len + 1];

        /**
         * 初始化
         * dp[0]对应了空串, 空串的翻译方法也只有一个, 那就是什么都翻译不出来
         * dp[1]对应了s.substring(0,1), 第一个字符, 显然只有一种译法
         */
        dp[0] = 1;
        dp[1] = 1;

        /**
         * 递推,当前字符为s.charAt(i-1)
         *
         * (1)当前字符不为0, 且按一个字符单算, 那么dp[i] = dp[i-1]
         * (2)若当前字符为0, 只能和前一个字符按两个字符算, 那么需要和前一个字符综合判断是否符合1-26的范围要求
         *    如果符合, 那么dp[i] = dp[i-2] + dp[i-1]
         *    如果不符合,那就按条件(1)来算
         *
         *
         */
        for (int i = 2; i <= s.length(); i++) {
            if (s.charAt(i - 1) != '0') {
                if (s.charAt(i - 2) == '1'
                        || (s.charAt(i - 2) == '2' && s.charAt(i - 1) < '7')) {
                    dp[i] = dp[i - 2] + dp[i - 1];
                } else {
                    dp[i] = dp[i - 1];
                }
            } else {
                //当前位为0, 那么只能结合前一位形成一个方案
                //所以, 前一位合法的情况下, dp[i] = dp[i - 2]
                //前一位若不合法,
                if (s.charAt(i - 2) == '1'
                        || s.charAt(i - 2) == '2') {
                    dp[i] = dp[i - 2];
                } else {
                    dp[i] = 0;
                }
            }
        }
        return dp[len];
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("120110"));
    }
}

