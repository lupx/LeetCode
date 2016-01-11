package com.leetcode.number214.ShortestPalindrome;

/**
 * Given a string S, you are allowed to convert it to a palindrome by adding characters in front of it.
 * Find and return the shortest palindrome you can find by performing this transformation.
 * For example:
 * Given "aacecaaa", return "aaacecaaa".
 * Given "abcd", return "dcbabcd".
 *
 * Created by Peixin Lu on 15/12/26.
 */
public class Solution {

    /**
     * 规定得很死, 只能在它前面加尽量少的字符, 使其变成一个回文串并返回.
     * 这就说明, s从左往右肯定能找到一个最大回文串(肯定能找到是因为一个字符也是回文串)
     * 找到最大回文串后, 其后面的字符逆序加到前面就是结果
     * 一个end指针, 从s最后一位开始往0后退, 每一位看看到第一位之间是不是一个回文串, 若不是end--
     * 若找到回文串, end所处位置之后的字符串逆序, append到开头即可
     *
     * @param s
     * @return
     */
    public static String shortestPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) return s;

        int end = s.length() - 1;
        while (end > 0) {
            int low = 0;
            int high = end;
            while (high > low) {
                if (s.charAt(high) == s.charAt(low)) {
                    high--;
                    low++;
                } else break;
            }
            if (high - low > 0) {
                //说明没找到
                end--;
            } else {
                //说明找到了
                break;
            }
        }
        //此时的end所停留位置,就是最大回文串最后一个字符位置
        //end后的字串逆序
        if (end == s.length() - 1) return s;
        String tmp = reversString(s.substring(end + 1));
        StringBuilder sb = new StringBuilder(tmp);
        sb.append(s);
        return sb.toString();
    }

    private static String reversString(String s) {
        char[] c = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (j > i) {
            char tmp = c[j];
            c[j] = c[i];
            c[i] = tmp;
            i++;
            j--;
        }
        return new String(c);
    }

    public static void main(String[] args) {
        System.out.println(shortestPalindrome("aab"));
    }
}
