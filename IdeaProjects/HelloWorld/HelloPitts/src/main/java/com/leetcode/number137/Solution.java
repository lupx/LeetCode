package com.leetcode.number137;

/**
 * Given an array of integers, every element appears three times except for one. Find that single one.
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 从重复3次的数字中, 寻找落单的数
     * 用hash表,可以O(n)时间+O(n)空间解决
     *
     * 现在要求用O(1)空间解决
     * 位操作:
     * 始终没搞懂!!!
     *
     *
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {

        int a=0;
        int b=0;
        for(int c:nums){
            int ta=(~a&b&c)|(a&~b&~c);
            b=(~a&~b&c)|(~a&b&~c);
            a=ta;
        }
        //we need find the number that is 01,10 => 1, 00 => 0.
        return a|b;
    }


    public static void main(String[] args) {
    }
}

