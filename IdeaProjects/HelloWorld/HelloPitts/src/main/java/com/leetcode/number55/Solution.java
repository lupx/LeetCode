package com.leetcode.number55;


/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 * Created by PeixinLu on 15/10/11.
 */
public class Solution {

    /**
     * 动态规划!
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        /**
         * can[i]代表从开始位置能否跳到i位置
         */
        boolean[] can = new boolean[nums.length];

        /**
         * 初始化
         */
        can[0] = true;

        for (int i = 1; i < nums.length; i++) {
            /**
             * 下面这个if是为了对付最差情况: 连续相同数字
             * 那么, 条件必须苛刻:
             * 首先当前i位要等于i-1位数字
             * 其次, i-1位要确实能达到
             * 第三, 从i-1位要能跳到i位
             * 这三个条件全部满足, 才能够把can[i]置为true
             * 否则, 就走下面流程一个一个判断
             */
            if (nums[i] == nums[i - 1] && can[i - 1] && nums[i - 1] >= 1) {
                can[i] = true;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (can[j] && nums[j] >= i - j) {
                    can[i] = true;
                    break;
                }
            }
        }

        return can[nums.length - 1];
    }


    public static void main (String[] args) {
        System.out.println(canJump(new int[]{2,1,0,1,1}));
    }
}
