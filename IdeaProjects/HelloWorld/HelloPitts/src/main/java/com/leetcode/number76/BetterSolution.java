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
public class BetterSolution {

    /**
     * Sliding Window:
     * i和j起初都在0位置,然后j往右数,数到刚好能覆盖t串的时候, i往右收缩
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if (s == null || s.equals("")) return "";
        /**
         * tmap中保存了t中各个字符,以及其个数
         * 显然, 在后面滑动窗口遍历s的时候:
         * 任何时候只要j++的时候遇到了tmap中还有的字符, tmap该字符-1, 同时count++
         * 只要count==lent了, 就说明已经能完全覆盖了
         */
        Map<Character, Integer> tmap = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
        }

        int lent = t.length();
        int lens = s.length();
        int i = 0, j = 0;
        int minWinLeft = -1, minWinRight = -1;
        int count = 0;
        while (j <= lens) {//窗口开始滑动
            if (count < lent && j < lens) {
                //说明还没找完, j要继续右移
                char thisChar = s.charAt(j);
                if (tmap.containsKey(thisChar)) {
                    tmap.put(thisChar, tmap.get(thisChar) - 1);
                    if (tmap.get(thisChar) >= 0) {
                        count++;
                    }
                }
                j++;
            } else if (count == lent) {
                //刚好够了, i开始往右边滑动收缩!
                char thisChar = s.charAt(i);
                if (tmap.containsKey(thisChar)) {
                    tmap.put(thisChar, tmap.get(thisChar) + 1);
                    if (tmap.get(thisChar) > 0) {//这个时候说明,目前的i应当就是一个符合条件滑动窗口的左边界
                        count--;
                        //更新minWinLeft和minWinRight
                        if ((minWinLeft == -1 && minWinRight == -1)
                                || (minWinRight - minWinLeft) > (j - i)) {
                            minWinLeft = i;
                            minWinRight = j;
                        }
                    }
                }
                i++;
            } else {
                break; //j到达了s尽头, 同时count != lent
            }
        }
        return (minWinLeft >= 0 && minWinRight >= 0) ? s.substring(minWinLeft, minWinRight) : "";
    }

    public static void main(String[] args) {
        System.out.println(minWindow("a", "a"));
    }
}

