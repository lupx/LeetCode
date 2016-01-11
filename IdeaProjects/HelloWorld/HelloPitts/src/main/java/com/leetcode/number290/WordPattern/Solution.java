package com.leetcode.number290.WordPattern;


/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * Created by Peixin Lu on 16/1/9.
 */
public class Solution {

    /**
     * 用hashMap来做就行了..
     * 或者数组保存abba的字符, 然后相同字符对应位置找str里对应的string
     *
     * 总之就是mapping的思想
     * @param pattern
     * @param str
     * @return
     */
    public boolean wordPattern(String pattern, String str) {
        //TODO
        return false;
    }

    public static void main(String[] args) {
    }
}
