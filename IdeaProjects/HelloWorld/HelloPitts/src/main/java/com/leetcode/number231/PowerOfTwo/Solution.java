package com.leetcode.number231.PowerOfTwo;

import java.util.*;

/**
 * 判断一个数是否是2的幂
 * 用位操作实现
 * Created by Peixin Lu on 15/12/31.
 */
public class Solution {

    /**
     * 位操作实现
     * 2的幂的数的共同特征是, 只有最高位是1, 其他位全部为0
     * 借助这个特征来做
     * beat 19.33%
     * @param n
     * @return
     */
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        while (n != 1) {
            if ((n & 1) == 1) return false;
            n >>>= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1));
    }
}
