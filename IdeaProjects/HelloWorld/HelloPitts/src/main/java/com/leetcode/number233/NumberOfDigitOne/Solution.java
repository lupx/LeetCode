package com.leetcode.number233.NumberOfDigitOne;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 * 找小于等于n的所有数字中(十进制表示法), 数字"1"出现的总次数.
 * Created by Peixin Lu on 15/12/31.
 */
public class Solution {

    /**
     * 思路在于算所有n个数里, 各个位累积有多少个1
     * (1) 个位的1: 首先其他各位全部为0,个位为1的时候(就是1), 有1个1. 然后所有数字有多少个10, 就会有多少个个位1(因为每个10个数中, 有一个个位1)!
     * (2) 十位的1: 所有数字有多少个100, 就对应多少个十位1. 此外,还必须考虑十位为1的时候, 有10个1.
     * beat 13.81%
     * @param n
     * @return
     */
    public static int countDigitOne(int n) {
        if (n <= 0) return 0;
        if (n <= 0) return 0;
        int q = n, x = 1, ans = 0;
        while (q > 0) {
            int digit = q % 10;
            q /= 10;
            ans += q * x;
            if (digit == 1) ans += n % x + 1;
            if (digit >  1) ans += x;
            x *= 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countDigitOne(99));
    }
}
