package com.leetcode.number132;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * 返回最少分割次数
 * Created by Peixin Lu on 15/11/6.
 */
public class SolutionBetter {

    /**
     * 计算最少分割次数
     * 39ms, beats 33.10%, 为什么还是这么慢!!!
     * @param s
     * @return
     */
    public static int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        /**
         * parlin[i][j]代表了s{i,j}子串是否是一个回文串
         */
        boolean parlin[][] = getPalinMatrix(s);

        /**
         * dp[i]代表了s{0,i}子串的最少分割数,
         * 所以, 最后返回dp[n-1]即可
         */
        int[] dp = new int[n];

        for (int j = 0; j < n; j++) {
            int minCut = Integer.MAX_VALUE;
            for (int i = 0; i <= j; i++) {
                if (parlin[i][j]) {
                    //i到j可以作为一个分割
                    minCut = Math.min(minCut, i == 0 ? 0 : dp[i - 1] + 1);
                    if (minCut == 0) break;
                }
            }
            dp[j] = minCut;
        }
        return dp[n-1];
    }

    private static boolean[][] getPalinMatrix(String s) {
        boolean[][] parlin = new boolean[s.length()][s.length()];
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || parlin[i + 1][j - 1])) {
                    parlin[i][j] = true;
                }
            }
        }
        return parlin;
    }


    public static void main(String[] args) {
        System.out.println(minCut("aaaaaaa"));
    }
}

