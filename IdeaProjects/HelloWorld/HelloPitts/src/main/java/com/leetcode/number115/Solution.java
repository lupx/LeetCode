package com.leetcode.number115;

/**
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of
 * the characters without disturbing the relative positions of the remaining characters.
 * (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * 找s中, t这个子序列出现的不同方式.
     * DP解
     * int dp[i][j]代表, s前i个字符中, t前j个字符出现的不同子序列数
     * 15ms, beat 73%
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct(String s, String t) {
        if (s == null || s.length() == 0) return 0;
        if (t.length() == 0) return 1;

        int lens = s.length();
        int lent = t.length();
        int[][] dp = new int[lens + 1][lent + 1];

        /**
         * 初始化
         */
        dp[0][0] = 1;

        /**
         * s为空串时, t如何匹配?
         */
        for (int j = 1; j <= lent; j++) {
            dp[0][j] = 0;
        }

        /**
         * t为空串时, 永远都是1
         */
        for (int i = 1; i <= lens; i++) {
            dp[i][0] = 1;
        }

        /**
         * 递推公式:
         * 设 s.charAt(i-1)为 cs, t.charAt(j-1)为ct;
         * 那么, 每次比较的实际就是这两个字符
         * 当cs!=ct时, dp[i][j] = dp[i - 1][j]
         * 当cs == ct时, dp[i][j] = dp[i-1][j] + dp[i - 1][j - 1]
         */
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lent; j++) {
                char cs = s.charAt(i - 1);
                char ct = t.charAt(j - 1);
                if (cs != ct) dp[i][j] = dp[i - 1][j];
                else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        return dp[lens][lent];
    }

    public static void main(String[] args) {
        System.out.println(numDistinct("aacaacca", "ca"));
    }
}

