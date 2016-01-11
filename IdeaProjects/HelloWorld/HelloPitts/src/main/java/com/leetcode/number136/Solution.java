package com.leetcode.number136;

/**
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 寻找落单的数
     * 用hash表,可以O(n)时间+O(n)空间解决
     *
     * 现在要求用O(1)空间解决
     * 位操作:
     * 遍历一遍, 逐数字异或XOR, 最后的结果就是所求数字
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            res ^= nums[i];
        }
        return res;
    }


    public static void main(String[] args) {
    }
}

