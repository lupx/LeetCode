package com.leetcode.number132;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 * 返回最少分割次数
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 计算最少分割次数
     * 直觉来看是用DP来解:
     *  dp[i]代表s前i个字符最少的分割数,
     *  显然, dp[0] = -1
     *  递推分析:
     *  i每推进一位, 需要j从0-i,判断s.substring(j,i)的回文情况.
     *  第一个满足判断为true的j, dp[i] = dp[j] + 1;
     * 算法是没错, 但是TLE, 对全是相同元素的字符串
     * @param s
     * @return
     */
    public static int minCut(String s) {
        if (s == null || s.length() == 0) return 0;
        /**
         * dp[i]: 前i个字符中存在的最少分割数
         */
        int[] dp = new int[s.length() + 1];
        dp[0] = -1;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) != s.charAt(i - 1)) continue;
                if (isPalindrome(s.substring(j, i))) {
                    dp[i] = dp[j] + 1;
                    break;
                }
            }
        }

        return dp[s.length()];
    }

    private static boolean isPalindrome(String s) {
        if (s.equals("")) return true;
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }


    public static void main(String[] args) {
        System.out.println(minCut("aaaaaaa"));
    }
}

