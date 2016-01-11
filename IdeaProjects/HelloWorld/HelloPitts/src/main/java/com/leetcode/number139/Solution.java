package com.leetcode.number139;

import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, determine if s can be segmented into
 *    a space-separated sequence of one or more dictionary words.
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {


    /**
     * 回溯和DP都能做
     * 这里用DP做
     * boolean dp[i]代表: s.substring(0,i)是否是可分的
     *
     * @param s
     * @param wordDict
     * @return
     */
    public boolean wordBreak(String s, Set<String> wordDict) {
        int n = s.length();
        if (n == 0) return true;
        boolean[] dp = new boolean[n + 1];

        /**
         * 初始化
         */
        dp[0] = true;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;//找到一个就行
                }
            }
        }
        return dp[n];
    }



    public static void main(String[] args) {
    }
}

