package com.leetcode.number44;

/**
 * Implement wildcard pattern matching with support for '?' and '*'.
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "*") → true
 * isMatch("aa", "a*") → true
 * isMatch("ab", "?*") → true
 * isMatch("aab", "c*a*b") → false
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * DP解法
     * boolean 二维数组, dp[i][j]
     * dp[i][j]代表, s{0,...,i-1}和p{0,...,j-1}这两个子串的匹配情况
     * 换句话说, dp[i][j]代表了,s前i个字符和p前j个字符的匹配情况
     * 所以dp行长度应为s.length+1, 列个数应为p.length+1
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        int lens = s.length();
        int lenp = p.length();

        boolean[][] dp = new boolean[lens + 1][lenp + 1];

        /**
         * 初始化
         * dp[0][0]表示两个空串匹配,显然true
         */
        dp[0][0] = true;//

        /**
         * 初始化第一行
         * dp[0][j]
         * 代表了,s为空串和p的匹配结果
         * 这个要看情况, 如果p是*,就能匹配.一旦p不是*了, 后面全部为false
         */
        for (int j = 1; j <= lenp; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            } else {
                dp[0][j] = false;
            }
        }

        /**
         * 初始化第一列
         * dp[i][0]
         * 代表了,s匹配一个空串p的情况
         * 显然全部为false
         */
        for (int i = 1; i <= lens; i++) {
            dp[i][0] = false;
        }

        /**
         * 边界处理完了,现在来推递推公式:如何由前一个dp状态推出现在的dp状态
         * 关键就在于p.charAt(j-1)这个字符, 我们把这个字符记为P,
         * 我们把s.charAt(i-1)记为S, 有几个情况:
         * (1) P != '*':
         *   只有在dp[i-1][j-1]为true的时候, P==S或者P=='?', dp[i][j]才能为true
         *   所以, dp[i][j] = (P == S || P=='?') && dp[i-1][j-1])
         *
         * (2) P == '*', 情况较复杂:
         *   由于'*'可匹配任意长度任意字符(空串到无穷长度), 首先下面情况, dp[i][j]肯定为true:
         *   (2.1)dp[i-1][j] = true. j-1能匹配到i-2,那多匹配一个i-1也无妨.
         *   (2.2)dp[i][j-1] = true. j-1此时匹配的是个空串, 也完全可以.
         *   (2.3)dp[i-1][j-1] = true. 此时, i-1和j-1之前的都匹配, P匹配S, 肯定没问题.
         *
         *   同时应该注意到,上面三个条件,任何一个成立, dp[i][j]肯定成立!
         *   所以综上, dp[i][j] = 2.1 || 2.2 || 2.3
         *
         */
        for (int i = 1; i <= lens; i++) {
            for (int j = 1; j <= lenp; j++) {
                if (p.charAt(j - 1) != '*') {
                    dp[i][j] = (p.charAt(j - 1) == s.charAt(i - 1)
                            || p.charAt(j - 1) == '?') && dp[i-1][j-1];
                } else {
                    dp[i][j] = dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1];
                }
            }
        }
        return dp[lens][lenp];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("a","a?"));
    }
}
