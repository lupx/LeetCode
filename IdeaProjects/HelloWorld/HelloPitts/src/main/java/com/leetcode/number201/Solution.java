package com.leetcode.number201;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * For example, given the range [5, 7], you should return 4.
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * 首先想到的办法是直接循环&, 果然超时了!
     * @param m
     * @param n
     * @return
     */
    public static int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        int rst = m;
        for (int i = m + 1; i <=n; i++) {
            rst &= i;
        }
        return rst;
    }

    /**
     * 看m到n之间每个数字的每一位, 只要含0, 该位肯定AND出来是0, 全部为1, 该位AND出来为1
     * @param m
     * @param n
     * @return
     */
    public static int rangeBitwiseAndv2(int m, int n) {
        int rst = n;
        for (int i = m; i <= n; i++) {
            int digit = 0;
            int x = i;
            while (x != 0) {
                if ((x & 1) == 0) rst -= Math.pow(2, digit);
                x >>= 1;
                digit++;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(rangeBitwiseAndv2(5,7));
    }
}

