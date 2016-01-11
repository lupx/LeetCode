package com.leetcode.number205;


import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the order of characters.
 *    No two characters may map to the same character but a character may map to itself.
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * 使用hashMap把各个字符的对应关系记录下来
     * 22ms, beat 74.58%
     * @param s
     * @param t
     * @return
     */
    public static boolean isIsomorphic(String s, String t) {
        int n = s.length();
        Map<Character, Character> map = new HashMap<>();
        Map<Character, Character> used = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            if (map.containsKey(sc)) {
                if (tc != map.get(sc)) return false;
            } else if (used.containsKey(tc)){
                if (sc != used.get(tc)) return false;
            } else {
                map.put(sc, tc);
                used.put(tc, sc);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("aa", "aa"));
    }
}
