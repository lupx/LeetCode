package com.leetcode.number260.SingleNumber3;

/**
 * 寻找单身狗:
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
 *   Find the two elements that appear only once.
 * For example:
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only constant space complexity?
 *
 * Created by Peixin Lu on 16/1/3.
 */
public class Solution {

    /**
     * 用hashTable做肯定很简单, 但是不符合题目要求. 题目要求用常数空间来做
     * 同时题目要求在线性时间内完成, 那么只能用位操作.
     * 典型的位操作题.
     * 先全部XOR一遍, 得到一个result, 这个result必定是两个单身狗数字XOR得到的.
     * beat 41.45%
     * @param nums
     * @return
     */
    public int[] singleNumber(int[] nums) {
        // go through the array and XOR every element, for example, result = 6 (3^5)
        int result = nums[0];
        for(int i = 1; i < nums.length; i++){
            result ^= nums[i];
        }
        // notice that 6^5 = 3, 6^3 = 5
        // now how to find 3 and 5 from 6
        int[] r = new int[2];
        // find the lowest bit of the result
        // let's say 6 (0110), -6 = 1010  0110 & 1010 = 0010
        int lowbit = result & -result;
        // since this bit from the result is 1, we can be sure that
        // a & lowbit and b & lowbit have different result
        for(int n : nums){
            if((n & lowbit) == 0) r[0] ^= n;
            else r[1] ^=n;
        }
        return r;
    }

    /**
     * 这个更好理解, 还是在结果里找了个最低位为1的
     * @param nums
     * @return
     */
    public int[] singleNumberv2(int[] nums) {
        if(nums==null||nums.length==0)
            return new int[0];
        int[] a = new int[2];
        int t1 = 0,t2 = 0,n = nums.length, t = 0, s = 1;
        for (int i = 0; i < n; i++) {
            t ^= nums[i];
        }
        while ((t & s) == 0) {
            s = s << 1;
        }

        for (int i = 0;i < n; i++) {
            if ((nums[i] & s) == 0)
                t1 ^= nums[i];
            else if ((nums[i] & s) != 0)
                t2 ^= nums[i];
        }
        a[0] = t1;
        a[1] = t2;
        return a;
    }

    public static void main(String[] args) {
    }
}