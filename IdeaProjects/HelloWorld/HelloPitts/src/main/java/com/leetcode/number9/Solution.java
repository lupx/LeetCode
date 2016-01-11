package com.leetcode.number9;

/**
 * Palindrome Integer
 * Determine whether the given int is a palindrome number
 * Note: No extra space could be used!!!
 * Created by Peixin Lu on 15/9/14.
 */
public class Solution {
    public static boolean isPalindrome(int x) {
        if(x < 0) {return false;} //负数肯定不是,直接返回
        if(x >= 0 && x < 10) {return true;}//0-9肯定是,直接返回

        //正数情况
        String str = String.valueOf(x);

        int count = 0;
        int i = 0, j = str.length() - 1;
        while(count < str.length() / 2) { //回文数,只需要匹配两边的就可以了. 奇数位数时,除去中间匹配两边.
            if(str.charAt(i) == str.charAt(j)) {
                count++;
                i++;
                j--;
            } else {
                return false; //一旦发现不匹配立马返回false
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(213123222));
    }
}
