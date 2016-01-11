package com.leetcode.number89;


import java.util.ArrayList;
import java.util.List;

/**
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code.
 * A gray code sequence must begin with 0.
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * Note:
 * For a given n, a gray code sequence is not uniquely defined.
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 *
 * Created by PeixinLu on 15/10/21.
 */
public class Solution {

    /**
     *
     * @param n
     * @return
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        int m = 1;
        result.add(0);
        while (m <= n) {
            for (int i = result.size() - 1; i >= 0; i--) {
                int a = result.get(i);
                if (m == 1) {
                    a += 1;
                } else {
                    a += 2 << (m - 2);
                }
                result.add(a);
            }
            m++;
        }
        return result;
    }


    public static void main(String[] args) {
        List<Integer> list = grayCode(0);
        for (Integer i : list) {
            System.out.println(i);
        }
    }
}

