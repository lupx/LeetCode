package com.leetcode.number87;

import java.util.Arrays;

/**
 * LeetCode87题, 拼凑串
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     *
     * 用递归做, 先检查两个串是否是anagrams, 如果不是直接跳过,如果是则拿两个子串进行对比
     * 如何检查是否anagrams:
     *    toCharArray, 然后排序,然后看两个数组是否相同, 若不同,就说明不是anagrams!
     * 然后对s1长度遍历, 在每个位置分割成立两部分,
     *
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isScramble(String s1, String s2) {
        if (isAnagrams(s1, s2)) {
            int len = s1.length();//equals to the length of s2
            if (len <= 3) return true;//3个元素的时候无论如何都true
            /**
             * 把s1从i位置分割成两部分, 同时把s2从i分割成两个部分
             * 四个部分交叉比较, 只要有true就是true
             * 四个全false就是false
             *
             */
            for (int i = 1; i < len; i++) {
                if (isScramble(s1.substring(0, i), s2.substring(0, i))
                        && isScramble(s1.substring(i), s2.substring(i))) {
                    return true;
                }
                if (isScramble(s1.substring(0, i), s2.substring(len - i))
                        && isScramble(s1.substring(i), s2.substring(0, len - i))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 这应该是目前最简洁的anagrams的判断了!
     * 转为char[], 排序, 然后调用库函数看两个排序后数组是否相同!
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isAnagrams(String s1, String s2) {
        if (s1 == null || s2 == null || s1.equals("") || s2.equals("")) return false;
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if (Arrays.equals(c1, c2)) return true;
        else return false;
    }


    public static void main(String[] args) {
        System.out.println(isScramble("great", "tegra"));
    }
}

