package com.leetcode.number28;

/**
 * Implement strStr().
 * Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 *
 * 给两个串一个是目标串,一个是寻找串.
 * 找出在目标串中,寻找串第一次出现时的开始index
 *
 * 比如, haystack = "apple", need = "le", 那么返回3
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {

    /**
     *
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if(haystack == null || haystack.length() < needle.length()) return -1;
        if(needle.equals("")) return 0;

        int rs = -1;
        for (int i = 0; i < haystack.length(); ) {
            if(haystack.charAt(i) != needle.charAt(0)) {
                i++;
                continue;
            }
            //相等了
            int j = 1;
            boolean flag = false;
            if(haystack.length() - i < needle.length()) return -1;
            for( ; j < needle.length(); j++) {
                if(haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                return i;
            } else {
                i++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        String haystack = "apppppple";
        String needle = "pp";
        System.out.println(strStr(haystack, needle));
    }
}
