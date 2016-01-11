package com.leetcode.number151;

/**
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Update (2015-02-12):
 * For C programmers: Try to solve it in-place in O(1) space.
 *
 * Clarification:
 * What constitutes a word?
 *  A sequence of non-space characters constitutes a word.
 *  Could the input string contain leading or trailing spaces?
 *  Yes. However, your reversed string should not contain leading or trailing spaces.
 *  How about multiple spaces between two words?
 *  Reduce them to a single space in the reversed string.
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 先去掉头尾空格
     * 用空格划分
     * beats 89.35%
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        s = s.trim();
        String[] ss = s.split(" ");
        if (ss.length == 1) return s;

        int i = 0;
        int j = ss.length - 1;
        while (i < j) {
            swap(ss, i ,j);
            i++;
            j--;
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < ss.length; k++) {
            if (!ss[k].equals("")) {
                sb.append(ss[k]).append(" ");
            }
        }
        return sb.toString().trim();
    }

    private void swap(String[] ss, int i, int j) {
        String tmp = ss[i];
        ss[i] = ss[j];
        ss[j] = tmp;
    }

    public static void main(String[] args) {
    }

}

