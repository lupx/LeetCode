package com.leetcode.number273.Integer2EnglishWord;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 寻找丢失的数:
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * For example,
 * Given nums = [0, 1, 3] return 2.
 * Note:
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 * Created by Peixin Lu on 16/1/7.
 */
public class Solution {

    /**
     * 换个角度思考就行了, 我们把nums按位异或, 然后再和0-n所有数字异或, 其实这个问题就变成了
     * 0 - n这n+1个数里, 除了一个数字只出现了一次外, 其他数字全部出现两次. 要求找这个一次的数字!
     * 就变化成了找single number!
     * beat 44.32%
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int rst = nums[0];
        for (int i = 1; i < nums.length; i++) {
            rst ^= nums[i];
        }

        for (int i = 0; i < nums.length + 1; i++) {
            rst ^= i;
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(missingNumber(new int[]{9,8,2,3,4,5,7,0,1}));
    }
}