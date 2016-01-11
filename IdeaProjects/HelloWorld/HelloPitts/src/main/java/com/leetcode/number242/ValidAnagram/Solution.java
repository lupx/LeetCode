package com.leetcode.number242.ValidAnagram;

import java.util.*;

/**
 * 判断s和t是否是变形词
 * 三个解法:
 * 一个是hashTable挨个比对
 * 一个是char数组排序, 然后看两个数组是否相同. 这个解法更优雅, 然而问题在于, 对很长的string, 效率应该比第一个低.
 * 一个是new两个26位长的int数组, int[i]代表26个字符在string中出现的次数, 然后一个遍历, 将s和t中各个字符出现次数更新到两个数组中. 最后两个数组比较下是否equal. 也很优雅!
 *
 * Created by Peixin Lu on 16/1/1.
 */
public class Solution {

    /**
     *
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        return true;
    }

    public static void main(String[] args) {
        char[] a = new char[]{'a','b','c'};
        char[] b = new char[]{'a','b','c'};
        System.out.println(Arrays.equals(a, b));//两个数组比较元素, 应该用这个
    }
}
