package com.leetcode.number50;

/**
 * Implement pow(x, n).
 * 实现乘方, 也即返回x的n次方结果
 * Created by PeixinLu on 15/10/11.
 */
public class Solution {

    /**
     * 关键还是看n, 二分法+递归
     * 当n < 0的时候, 返回x的n次方的倒数
     * 当n > 0的时候, 返回x的n次方
     * 同时要注意n溢出的可能性
     *
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n < 0 && n > Integer.MIN_VALUE) {
            return 1 / myPow(x, -n);
        }
        if (n == Integer.MIN_VALUE) {
            return 1 / myPow(x * x, n >> 1);
        }
        //走到这里, n肯定为正数
        boolean isOdd = false;
        if (n % 2 != 0) isOdd = true;

        if (isOdd) return x * myPow(x * x, n >> 1);
        else return myPow(x * x, n >> 1);
    }

    public static void main (String[] args) {
        System.out.println(myPow(8.88023, 0));
    }


}
