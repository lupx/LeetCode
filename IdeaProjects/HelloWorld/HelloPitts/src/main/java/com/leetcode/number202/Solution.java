package com.leetcode.number202;

import java.util.HashSet;
import java.util.Set;

/**
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay),
 * or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * hashTable + math
     * 每一次循环把得到的结果计入hashTable. 不过不是happy数,那么某个结果总会出现在hashTable,这就是一个循环, 就可以返回false了
     * 否则,得到1就返回true
     * @param n
     * @return
     */
    public static boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            int m = 0;
            while (n != 0) {
                int tmp = n % 10;
                m += Math.pow(tmp, 2);
                n /= 10;
            }
            if (m == 1) break;
            if (set.contains(m)) return false;
            set.add(m);
            n = m;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(21));
    }
}

