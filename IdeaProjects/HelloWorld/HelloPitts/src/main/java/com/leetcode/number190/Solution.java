package com.leetcode.number190;

/**
 * Reverse bits of a given 32 bits unsigned integer.
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
 * return 964176192 (represented in binary as 00111001011110000010100101000000).
 *
 *
 * Follow up:
 * If this function is called many times, how would you optimize it?
 *
 *
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    // you need treat n as an unsigned value
    /**
     * 比特翻转.
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        String before = Integer.toBinaryString(n);
        char[] chars = before.toCharArray();
        //补0

        //翻转

        //输出成Integer, 并返回

        return 0;
    }

    /**
     * 大神的答案
     * 其实就是遍历n的各个位, 看看是不是1
     * 如果第i位是1, res的第31-i位就是1.
     * 这样相当于就是翻转了
     *
     * 另外, 注意>>>, 是无符号右移位操作
     *
     * java中没有<<<的操作!
     *
     * beat 46.94%
     * @param n
     * @return
     */
    public int reverseBits_AWESOME(int n) {
        int res = 0;
        int count = 31;
        while (n != 0) {
            if ((n & 1) == 1) {
                res = res + (1 << count);
            }
            n >>>= 1;
            count--;
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(43261596));
    }

}

