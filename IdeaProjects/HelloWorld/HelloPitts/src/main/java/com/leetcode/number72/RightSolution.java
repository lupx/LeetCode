package com.leetcode.number72;

/**
 * Created by PeixinLu on 15/10/16.
 */
public class RightSolution {
    /**
     * 正确的DP解法
     * 注意,DP解法从来都是直接面向结果的.
     * 所以,这里的dp[i][j]代表了word1的前i个字符和word2的前j个字符比较,需要做的最少改变次数
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;//前i个word1字符和空串word2比,每一位代表需要delete的个数
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;//空串word1和word2前j个字符比,每一位代表需要insert的个数
        }

        /**
         * dp递推公式:
         * 当word1.i == word2.j时, dp[i][j] = dp[i-1][j-1]
         * 当word1.i != word2.j时:
         * 有三种操作, 选择哪一种操作基于哪一种操作前一个状态所需改动次数最小.
         * (1)dp[i][j-1]最小, 选择insert, dp[i][j] = dp[i][j-1] + 1
         * (2)dp[i-1][j]最小, 选择delete, dp[i][j] = dp[i-1][j] + 1
         * (3)dp[i-1][j-1]最小,选择replace, dp[i][j] = dp[i-1][j-1] + 1
         */
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //此时变化次数不变
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int insert = dp[i][j-1] + 1;
                    int delete = dp[i-1][j] + 1;
                    int replace = dp[i-1][j-1] + 1;
                    dp[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("", ""));
    }
}

