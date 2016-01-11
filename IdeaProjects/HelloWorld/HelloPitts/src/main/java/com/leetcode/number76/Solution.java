package com.leetcode.number76;


import java.util.HashMap;
import java.util.Map;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 *
 * Note:
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * 如无符合条件窗口,返回空串
 * 如果有多个窗口, 题目保证只会有一个最小窗口
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 把T各个字符入hashmap
     * 然后, 在S中挨个字符找, 只要找到在tmap中有的字符, 记录起点start, tmap删除此字符(或者减少其个数)
     *     然后在此起点后继续挨个找, 如找到第二个, tmap继续删除(或减少)
     * 如果在tmap还未空的情况下, S遍历完了, 就说明没有窗口, return ""
     * 此算法会TLE!!!
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if (s == null || s.equals("")) return "";
        Map<Character, Integer> tmap = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            Integer a = tmap.getOrDefault(t.charAt(i), 0);
            a++;
            tmap.put(t.charAt(i), a);
        }

        int minWindowStart = 0;//最小窗口起点
        int minWindowSize = Integer.MAX_VALUE;

        //遍历s,遇到在tmap中有的字符就开始第二个指针的遍历
        for (int i = 0; i < s.length(); i++) {
            if (tmap.containsKey(s.charAt(i))) {
                int windowSize = 0;
                int start = 0;
                for (int j = i; j < s.length(); j++) {//开始计算窗口
                    start = i;//当前窗口起点, 显然从i开始
                    int second = 0;//当前窗口第二个点, 用于更新窗口
                    windowSize++;//开始计算当前窗口大小
                    Map<Character, Integer> pickmap = new HashMap<Character, Integer>();
                    if (tmap.containsKey(s.charAt(j))) {//遇到了某个包含于tmap中的字符
                        if (j != i && second == 0) second = j;//记录第二个位置
                        Integer b = pickmap.getOrDefault(s.charAt(j), 0);
                        b++;
                        pickmap.put(s.charAt(j), b);//已选加上
                        //tmap中更新
                        int left = tmap.getOrDefault(s.charAt(j), 0);
                        if (left == 1) tmap.remove(s.charAt(j));
                        else {
                            left--;
                            tmap.put(s.charAt(j), left);
                        }
                        if (tmap.size() == 0) {
                            break;//有点贪心的味道, 只要找到足够的就退出, 不用做剩余的操作了
                        }
                    } else if (pickmap.containsKey(s.charAt(j))
                            && s.charAt(i) == s.charAt(j)) {
                        //tmap中无, 已选里有, 且是start位置的相同, 可以更新
                        //先更新windowSize
                        windowSize = windowSize - (second - start);
                        //再更新start和second
                        start = second;
                        second = j;
                    }
                }//j找完了
                if (tmap.size() == 0) {
                    if (windowSize < minWindowSize) {
                        minWindowSize = windowSize;
                        minWindowStart = start;
                    }
                }
                if (tmap.size() > 0) {
                    //tmap中还剩, 说明本轮没找全, 说明剩余已经没有可找的了
                    tmap.clear();
                }
                //tmap补回
                for (int k = 0; k < t.length(); k++) {
                    Integer a = tmap.getOrDefault(t.charAt(k), 0);
                    a++;
                    tmap.put(t.charAt(k), a);
                }
            }
        }
        if (minWindowSize == Integer.MAX_VALUE) {
            return "";//始终没找到
        } else {
            return s.substring(minWindowStart, minWindowStart + minWindowSize);
        }
    }

    public static void main(String[] args) {
        System.out.println(minWindow("coobdafcxxmnaabqw", "abc"));
    }
}

