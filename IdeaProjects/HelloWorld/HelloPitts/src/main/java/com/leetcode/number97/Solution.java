package com.leetcode.number97;

/**
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * Created by Peixin Lu on 15/10/22.
 */
public class Solution {

    /**
     * 说不用DP做都不信!
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() == 0) {
            return s3.length() == 0;
        }
        int len1 = s1.length();
        int len2 = s2.length();
        if (len1 + len2 != s3.length()) return false;//先保证长度合理, 才能往后面走

        /**
         * dp[i][j]代表当前,s1前i个字符和s2前j个字符是否能够组成s3前i+j个字符
         * 所以,dp[len1][len2]就是s3和s1,s2全部比完的结果
         * 最后return dp[len1][len2]即可
         */
        boolean[][] dp = new boolean[len1 + 1][len2 + 1];

        /**
         * 初始化
         * dp[0][0]代表两个空串和一个空串做比较, 为true
         */
        dp[0][0] = true;
        for (int j = 1; j <= len2; j++) {
            //s1为空, 初始化
            dp[0][j] = dp[0][j - 1]
                    && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        for (int i = 1; i <= len1; i++) {
            //s2为空, 初始化
            dp[i][0] = dp[i - 1][0]
                    && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        /**
         * 递推:
         * 对于每个[i][j]必然只对应s3其中当前字符.
         * 首先, 需要看dp[i-1][j]和dp[i][j-1]两种情况哪个是true.
         * 如果两个都不true, dp[i][j]肯定填false
         * 如果其中一个true, 那就需要比较, 要么是s1.charAt(i-1)和s3当前相同, 要么是s2.charAt(j-1)和s3当前相同:
         *    如果dp[i-1][j]为true, 那么dp[i][j]取决于比较s1.charAt(i-1)是否和s3当前相等
         *    如果dp[i][j-1]为true, 那么dp[i][j]取决于比较s2.charAt(j-1)是否和s3当前相等
         *    如果两个都为true, 那么dp[i][j]取决于两种情况的或结果, 不管哪个成功了, 都为true
         */
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                char c3 = s3.charAt(i + j - 1);
                boolean match1 = dp[i - 1][j];//当前就看s1.charAt(i-1)和s3的匹配
                boolean match2 = dp[i][j - 1];//当前就看s2.charAt(j-1)和s3的匹配
                if (match1 || match2) {
                    //只要有一个为true, dp[i][j]都有可能为true
                    if (match1 && !match2) {
                        dp[i][j] = s1.charAt(i - 1) == s3.charAt(i + j - 1);
                    } else if (match2 && !match1) {
                        dp[i][j] = s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    } else {
                        dp[i][j] =
                                s1.charAt(i - 1) == s3.charAt(i + j - 1)
                                || s2.charAt(j - 1) == s3.charAt(i + j - 1);
                    }
                } else {
                    //两个都false
                    dp[i][j] = false;
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(isInterleave("db", "b", "cbb"));
    }
}

