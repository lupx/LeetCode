package com.leetcode.number283.MoveZeros;


/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Created by Peixin Lu on 16/1/9.
 */
public class Solution {

    /**
     * 必须原地完成,不能用额外空间:
     * 一趟遍历, 两个指针zero 和 i
     * zero永远指向0, i指向下一个非0数字
     * beat 13%
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int zero = 0;
        for (int i = 1; i < nums.length;) {
            while (nums[zero] != 0 && zero < nums.length - 1) {
                zero++;
            }
            while (nums[i] == 0 && i < nums.length - 1) {
                i++;
            }
            if (nums[zero] == 0 && nums[i] != 0 && zero < i) {
                swap(nums, i, zero);
                i = zero + 1;
            } else i++;
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public static void main(String[] args) {
        moveZeroes(new int[]{0,0,3,0,1,5,0});
        System.out.println("aaa");
    }
}
