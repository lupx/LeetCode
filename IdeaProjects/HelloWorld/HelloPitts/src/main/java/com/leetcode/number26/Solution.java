package com.leetcode.number26;

import com.leetcode.number25.ListNode;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 *
 * Created by PeixinLu on 15/9/21.
 */
public class Solution {

    /**
     * 遍历一遍,数重复的个数,然后nums.length-重复个数,即为所求
     * 但是,这个题太浪了!
     * 返回正确数字的同时, 它还要求把原有的数组改掉, 假设返回5, 说明有5个不同数字,它还要求nums 前5个数字就是这5个不同的数字
     * @param nums
     * @return 去重后个数
     */
    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int dup = 0;
        int jump = 0;
        int i = 0;
        while (jump < nums.length) {
            while (jump < nums.length - 1 && nums[jump] == nums[jump + 1]) {
                dup++;
                jump++;
            }
            nums[i++] = nums[jump++];
        }
        return nums.length - dup;
    }

    public static void main(String[] args) {
        int[] a = {1,1,1,2,3,3,3,4,5};
        System.out.println(removeDuplicates(a));
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }
}
