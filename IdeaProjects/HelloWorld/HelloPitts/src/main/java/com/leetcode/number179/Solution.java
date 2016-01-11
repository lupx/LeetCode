package com.leetcode.number179;

import java.util.*;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * 注意: 可以有0
 * Created by Peixin Lu on 15/11/27.
 */
public class Solution {

    /**
     * 解法太牛逼!
     * @param nums
     * @return
     */
    public static String largestNumber(int[] nums) {
        int max = 0;
        String rst = "";
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (max < nums[i]) max = nums[i];
            strs[i] = nums[i] + "";
        }
        /**
         * 用lambda表达式
         * 利用了两个str比大小等同于数字比大小
         */
        Arrays.sort(strs, (s1, s2) -> (s2 + s2).compareTo(s1 + s2));
        for (int i = 0; i < nums.length; i++) rst += strs[i];
        return max == 0 ? "0" : rst;
    }

    public static void main(String[] args) {
        System.out.println(largestNumber(new int[]{1,4,6,8,81,810,9}));
    }

}

