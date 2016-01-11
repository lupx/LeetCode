package com.leetcode.number29;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * If it is overflow, return MAX_INT.
 *
 * 什么时候会overflow?
 * divisor是int,所以divisor不可能<1或者>-1,那么还有什么时候会溢出?!
 * dividend == Integer.MIN_VALUE时,而divisor为-1. 那么相除后就会溢出.所以特殊对待!
 *
 * Created by PeixinLu on 15/10/1.
 */
public class Solution {

    /**
     * 用位移运算符,二分!这个题考察的其实是二分!!!
     * 经过测试divisor不会给0, 所以不用考虑0的情况
     * 什么时候会溢出?
     * dividend == Integer.MIN_VALUE, 同时 divisor是-1时, 就会溢出! 就这一个情况!
     * @param dividend
     * @param divisor
     * @return
     */
    public static int divide(int dividend, int divisor) {
        //溢出就出现在这里! 如果dividend为Integer.MIN_VALUE, divisor为-1, 那么除-1后就溢出
        if(divisor == -1) {
            if(dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
        }

        int sign = 1;
        if((dividend > 0 && divisor < 0)
                ||(dividend < 0 && divisor > 0))  sign = -1;

        //直接两个正数算商,最后补上符号即可
        long quo = 0;
        long twice = 1;
        long longDividendTmp = Math.abs((long)dividend);
        long longDivisorTmp = Math.abs((long)divisor);
        while(longDividendTmp > 0) {
            while (longDivisorTmp << 1 < longDividendTmp) {
                twice <<= 1;
                longDivisorTmp <<= 1;
            }

            longDividendTmp -= longDivisorTmp;
            quo += twice;
            longDivisorTmp = Math.abs((long)divisor);
            twice = 1L;
        }

        //longDividendTmp == 0的时候,说明刚好,那么quo就是所求
        //longDividendTmp < 0的时候, 说明quo多算了一个, quo-1就是所求
        return (int)(quo - (longDividendTmp == 0 ? 0 : 1)) * sign;
    }

    public static void main(String[] args) {
        long startTime=System.nanoTime();
        System.out.println(divide(-1,
                1));
        long endTime=System.nanoTime();
        System.out.println(endTime-startTime);
    }
}
