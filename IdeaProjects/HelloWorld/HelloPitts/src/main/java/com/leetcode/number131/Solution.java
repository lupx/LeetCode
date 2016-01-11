package com.leetcode.number131;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return all possible palindrome partitioning of s.
 * For example, given s = "aab",
 * Return
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 回溯, 枚举各种不同的分法, 每种分法实时判断是否palindrome:
     *    一旦遇到不符合条件的, 就返回
     *    如果全部分法都符合, 则记为一个解
     * 8ms on LeetCode
     * @param s
     * @return
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> rst = new ArrayList<>();
        if (s == null || s.length() == 0) return rst;
        List<String> tmp = new ArrayList<>();
        helper(s, rst, tmp);
        return rst;
    }

    private static void helper(String s, List<List<String>> rst, List<String> tmp) {
        if (s.length() == 0) {
            rst.add(new ArrayList<>(tmp));
            return;
        }

        if (s.length() == 1) {
            tmp.add(s);
            rst.add(new ArrayList<>(tmp));
            tmp.remove(tmp.size() - 1);
            return;
        }
        /**
         * 对s枚举各种不同的分法
         */
        for (int i = 1; i <= s.length(); i++) {
            String cur = s.substring(0, i);
            if (isPalindrome(cur)) {
                tmp.add(cur);
                helper(s.substring(i), rst, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
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
        String a = "aabbc";
        List<List<String>> rst = partition(a);
        for (List<String> l : rst) {
            StringBuilder sb = new StringBuilder();
            for (String str: l) {
                sb.append(str).append(",");
            }
            System.out.println(sb.toString());
        }
//        System.out.println(a.substring(0, 2));
    }
}

