package com.leetcode.number72;


/**
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)
 * You have the following 3 operations permitted on a word:
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 * Created by Peixin Lu on 15/10/15.
 */
public class Solution {

    /**
     * 说白了, 是在word2里找不必连续的word1中最长子串
     * 这个就可以用DP来做!
     * dp[i][j] 表示 word1中前i个字符(0->i-1)在word2前j(0->j-1)个字符中最长的子串
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];

        /**
         * 初始化
         */
        dp[0][0] = 0;
        for (int i = 1; i < word2.length(); i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < word1.length(); i++) {
            dp[i][0] = 0;
        }

        /**
         * 递推公式
         * dp[i][j] = ?
         * 有三个位置需要考虑: dp[i-1][j-1]/ dp[i-1][j] 和 dp[i][j-1]
         * 同时, 还要综合考虑word2.charAt(j-1)和word1.charAt(i-1)是否相同的情况,区分对待:
         * 当两个字符相同时:
         * (1) 如果dp[i-1][j] == dp[i-1][j-1] + 1, 说明当前j字符已经在word1中匹配掉了, 所以dp[i][j] = dp[i-1][j]
         *     这个条件下,还有个情况也需要讨论: dp[i][j-1]会不会影响上面的结果?
         *     考虑, 由于上面条件, 说明直到匹配到word2.charAt(j-1)的时候, 匹配长度才+1, 说明这个字符就是个被实际匹配的字符.
         *     那么显然, 在这个字符出现之前, dp[i][j-1] == dp[i-1][j-1], 所以其实不影响结果
         * (2) 如果dp[i-1][j]和dp[i-1][j-1]之间无上面的关系, 说明j字符在之前还没有参与过匹配,
         *     但是, dp[i][j-1]会对结果有影响, 因为word1.charAt(i-1)这个字符完全有可能在word2的前j-1个字符里匹配成功,
         *     那么word2中j字符其实对结果不产生影响,结果不变. 这种情况实际上就是dp[i][j-1] = dp[i-1][j-1] + 1.
         *     所以, 当dp[i][j-1] = dp[i-1][j-1] + 1的时候, dp[i][j] = dp[i][j-1]
         *
         * (3) 如果上面两个条件的关系都不成立, 那很简单, 就是dp[i][j] = dp[i-1][j-1] + 1
         * 我们这个时候再回顾上面的式子, 其实可以合并成一个式子: dp[i][j] = dp[i-1][j-1] + 1
         *
         * 当两个字符不同时, 不难分析得出:
         * dp[i][j] = dp[i-1][j]
         *
         */
        int start1 = 0;
        int end1 = 0;
        int start2 = 0;
        int end2 = 0;
        boolean started = false;
        for (int i = 1; i <= word1.length(); i++) {
            int changePosition = Integer.MAX_VALUE;
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    changePosition = j;
                    if (!started) {
                        start1 = i - 1;
                        start2 = j - 1;
                        started = true;
                    }
                    end1 = i - 1;
                    end2 = j - 1;
                } else {
                    if (j <= changePosition) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = dp[i][j - 1];
                    }
                }
            }
        }
        //最终, dp[word1.length][word2.length]保存着两个词最长匹配长度
        int length = dp[word1.length()][word2.length()];
        int shorter = Math.min(word1.length(), word2.length());
        int longer =  Math.max(word1.length(), word2.length());
        if (length == 0) {
            return longer;
        }

        //起始点左边的字符之间可以互换, 但是与终点右边独立
        int bigLeft = Math.max(start1, start2);
        int smallLeft = Math.min(start1, start2);
        int left = bigLeft - smallLeft + 1;

        //中间字符可以互换,但是与两边都独立
        int bigMid = Math.max(end1 - start1 + 1, end2 - start2 + 1);
        int smallMid = Math.min(end1 - start1 + 1, end2 - start2 + 1);
        int mid = bigMid - smallMid + 1;

//        if (shorter == length) {
//            return longer - shorter;
//        }
        return longer - shorter + (shorter - length);
    }

    public static void main (String[] args) {
        String word1 = "abdceccf";
        String word2 = "beccdfgh";
        System.out.println(minDistance(word1, word2));
    }

}
