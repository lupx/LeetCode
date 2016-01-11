package com.leetcode.number58;


/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * If the last word does not exist, return 0.
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example,
 * Given s = "Hello World",
 * return 5.
 * Created by Peixin Lu on 15/10/12.
 */
public class Solution {

    /**
     * 如果s长度为0,返回0
     * s中所有" "替换为"|"
     * 然后把s由|分割成String[], 取最后一个元素出来求长度即可
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        if (s == null) return 0;
        s = s.trim();
        if (s.equals("")) return 0;
        s = s.replaceAll(" ", "|");
        String[] strs = s.split("\\|");
        return strs[strs.length - 1].length();
    }

    public static void main (String[] args) {
        String a = "aa";
        System.out.println(lengthOfLastWord(a));
    }

}
