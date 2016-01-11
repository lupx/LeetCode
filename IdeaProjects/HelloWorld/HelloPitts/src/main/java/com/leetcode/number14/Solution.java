package com.leetcode.number14;


/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * Created by Peixin Lu on 15/9/14.
 */
public class Solution {

    /**
     * 分析一下情况:
     * (1)有空串,公共前缀肯定是空串
     * (2)若都没有公共前缀, 返回空串
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";

        boolean flag = true;
        StringBuilder sb = new StringBuilder();
        int count = 1;
        while(flag) {
            if(count > strs[0].length()) break;
            String tmpprefix = strs[0].substring(0,count);
            int i = 0;
            while(i < strs.length) {
                flag = strs[i].startsWith(tmpprefix);
                if(!flag) break;
                i++;
            }
            if(i == strs.length) {
                count++;
                sb = new StringBuilder("");
                sb.append(tmpprefix);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestCommonPrefix(new String[]{"abc","abce","abca","abcef"}));
//        System.out.println("abc".substring(0,3));
    }
}
