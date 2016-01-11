package com.leetcode.number125;

/**
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        s = s.replaceAll(" ", "");//把空格去掉
        String a = s.toLowerCase();
        //前后指针往中间走, 只要遇到不同的char, 就返回false, 否则如果循环结束, 返回true
        int i = 0;
        int j = a.length() - 1;
        while (i < j) {
            if (!Character.isLetter(a.charAt(i))
                    && !Character.isDigit(a.charAt(i))) {
                i++;
                continue;
            }
            if (!Character.isLetter(a.charAt(j))
                    && !Character.isDigit(a.charAt(j))) {
                j--;
                continue;
            }
            if (a.charAt(i) != a.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        String a = "AppLe";
        String b = a.toLowerCase();
        System.out.println(b);
    }
}

