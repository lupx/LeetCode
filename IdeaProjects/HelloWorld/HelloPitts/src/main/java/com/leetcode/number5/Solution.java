package com.leetcode.number5;

/**
 * Created by PeixinLu on 15/9/5.
 * Given a String, find the longest palindromic Substring.
 * For example, given s = "abcdefedcadfh", should return "cdefedc"
 * Assume that there will only one longest palindromic substring
 */
public class Solution {
    /**
     *
     * @param s THE given String
     * @return the longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        StringBuffer sb = new StringBuffer("adf");
        char[] ss = s.toCharArray();
        int current = 0;
        int p1=0, p2=0;
        int maxlen = 0;
        int thislen = 1;
        int[] pos = new int[2];
        while(current<ss.length-1 || (ss.length-1-current)>maxlen/2 ) {
            p1 = current - 1;
            p2 = current + 1;
            thislen = 1;
            while(p1>=0 && p2<=ss.length-1) {
                if(ss[p1] == ss[p2]) {
                    thislen += 2;
                    if(maxlen<thislen) {
                        //update the pos
                        maxlen = thislen;
                        pos[0] = p1;
                        pos[1] = p2;
                    }
                    p1--;
                    p2++;
                } else {
                    break;
                }
            }
            if(ss[current] == ss[current+1]){
                p1 = current;
                p2 = current + 1;
                thislen = 0;
                while(p1>=0 && p2<=ss.length-1) {
                    if(ss[p1] == ss[p2]) {
                        thislen += 2;
                        if(maxlen<thislen) {
                            //update the pos
                            maxlen = thislen;
                            pos[0] = p1;
                            pos[1] = p2;
                        }
                        p1--;
                        p2++;
                    } else {
                        break;
                    }
                }
                current++;
            } else {
                current++;
            }
        }

        return s.substring(pos[0], pos[1]+1);
    }


    public static void main(String[] args){
        String a = "ccc";
        System.out.println(longestPalindrome(a));
    }
}
