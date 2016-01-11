package com.leetcode.number81;


/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 如果有重复数字, 那么二分法将不再适用, 考虑下面情况(,为rotate点):
     * 111111111,111111111
     * 那么二分的时候, 左右两边和mid都相等, 如果确定该走哪边呢? 根本不可能!
     * 所以就退化成O(n)时间了
     *
     * @param nums
     * @param target
     * @return
     */
    public boolean search(int[] nums, int target) {
        boolean result = false;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) result = true;
        }
        return result;
    }

    public static void main(String[] args) {
    }
}

