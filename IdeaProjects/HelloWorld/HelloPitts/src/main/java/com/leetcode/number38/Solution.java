package com.leetcode.number38;

/**
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 1 is read off as "one 1" or 11.
 * 11 is read off as "two 1s" or 21.
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 *
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * 当前状态只跟前一状态有关, 可以考虑dp
     *
     * @param n
     * @return
     */
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        String pre = "1";
        StringBuilder sb;
        for (int i = 2; i <= n; i++) {
            //sb根据pre来构造, 然后pre = sb.toString, 循环继续
            int len = pre.length();
            int count = 0;
            char current = pre.charAt(0);
            sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                if (current == pre.charAt(j)) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(current);
                    current = pre.charAt(j);
                    count = 1;
                }
            }
            sb.append(count);
            sb.append(current);

            pre = sb.toString();
        }
        return pre;
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(7));
    }
}
