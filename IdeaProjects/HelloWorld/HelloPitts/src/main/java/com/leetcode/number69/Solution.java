package com.leetcode.number69;

/**
 *
 * Created by Peixin Lu on 15/10/14.
 */
public class Solution {

    /**
     * 求x平方根, 二分法做
     * 一个start, 一个end
     * 注意溢出的处理
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1 || x == 2) return 1;
        int start = 1;
        int end = x;
        while (start + 1 < end) {
            long mid = start + (end - start ) / 2;
            long tmp = mid * mid + 0L;
            if (tmp == x + 0L) return (int)mid;
            if (tmp > x + 0L) {
                end = (int)mid;
            } else {
                start = (int)mid;
            }
        }
        long t = (long)end * (long)end;//注意这里的处理!
        if (t <= x) {
            return end;
        } else {
            return start;
        }
    }

    public static void main (String[] args) {
        System.out.println(mySqrt(Integer.MAX_VALUE));
    }

}
