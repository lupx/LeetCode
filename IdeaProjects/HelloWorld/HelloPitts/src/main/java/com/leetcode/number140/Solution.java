package com.leetcode.number140;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 *
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 回溯法求解
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> rst = new ArrayList<>();
        if (s == null || s.length() == 0) return rst;
        if (wordDict.size() == 0) return rst;
        return helper(s, 0, wordDict, new List[s.length()]);
    }

    /**
     * 递归函数
     * @param s
     * @param start
     * @param wordDict
     * @param memo memp[i] saves the word break result that comes from s.substring(i + 1)
     */
    private static List<String> helper(String s, int start, Set<String> wordDict, List<String>[] memo) {
        List<String> rst = new ArrayList<>();
        if (start == s.length()) {
            rst.add("");
            return rst;
        }
        for (int i = start; i < s.length(); i++) {
            String cur = s.substring(start, i + 1);
            if (wordDict.contains(cur)) {
                //从递归结果中求得rst
                //然后遍历rst, 加上当前curStr
                List<String> rest = null;
                if (memo[i] != null) {
                    rest = memo[i];
                } else {
                    rest = helper(s, i + 1, wordDict, memo);
                    memo[i] = rest;
                }
                for (String str : rest) {
                    if (str.equals("")) rst.add(cur);
                    else {
                        rst.add(cur + " " + str);
                    }
                }
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        Set<String> wordDict = new HashSet<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("dog");
        List<String> rst = wordBreak("catsanddog", wordDict);
        for (String str : rst) {
            System.out.println(str);
        }
    }
}

