package com.leetcode.number67;

/**
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 * Created by Peixin Lu on 15/10/14.
 */
public class Solution {

    /**
     * 两个二进制数相加, 返回结果
     * 诸位相加, 结果==2, 进位+1
     * 直到其中一个完全处理完毕
     * 再检查carry是否为1
     * 如果还有carry, 则处理剩下的串, 直到结束
     * 最终把数组append进string, 返回
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        if (a.length() == 0 || a.equals("0")) return b;
        if (b.length() == 0 || b.equals("0")) return a;

        int lena = a.length();
        int lenb = b.length();

        int addlen = Math.min(lena, lenb);
        int i = lena - 1;
        int j = lenb - 1;
        int carry = 0;
        int[] rs = new int[addlen];
        while (addlen > 0) {
            int first = a.charAt(i) - '0';
            int second = b.charAt(j) - '0';
            int sum = first + second + carry;
            if (sum >= 2) {
                carry = 1;
            } else {
                carry = 0;
            }
            rs[addlen - 1] = sum % 2;
            addlen--;
            i--;
            j--;
        }
        StringBuilder sb = new StringBuilder();
        if (i == -1 && j == -1) {
            //长度相等
            sb.append(carry);
            for (int k = 0; k < rs.length; k++) {
                sb.append(rs[k]);
            }
            return sb.toString();
        }
        String rest;
        if (i == -1) {
            //b还剩
            rest = b.substring(0, b.length() - rs.length);
        } else {
            rest = a.substring(0, a.length() - rs.length);
        }

        int len = rest.length();
        int[] restArr = new int[len];
        while (len > 0) {
            int tmp = carry + (rest.charAt(len - 1) - '0');
            carry = tmp / 2;
            restArr[len - 1] = tmp % 2;
            len--;
        }
        if (carry != 0) sb.append(carry);
        for (int k = 0; k < restArr.length; k++) {
            sb.append(restArr[k]);
        }
        for (int k = 0; k < rs.length; k++) {
            sb.append(rs[k]);
        }
        return sb.toString();
    }

    public static void main (String[] args) {
        System.out.println(addBinary("101010011101110100110111", "111010010101101010"));
    }

}
