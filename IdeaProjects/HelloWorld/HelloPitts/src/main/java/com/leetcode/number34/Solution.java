package com.leetcode.number34;

/**
 * Given a sorted array of integers, find the starting and ending position of a given target value.
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * If the target is not found in the array, return [-1, -1].
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * 同样,暴力办法都可以在O(n)内找到,那么肯定不能用暴力了.
     * 那就只能是二分搜索.
     * 为了找范围,先找到第一个target,  再找到最后一个target, 范围不就有了么.
     * 所以, 其实就是2次二分搜索.
     *
     * @param nums 待查数组
     * @param target 待找数字
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) return res;
        int n = nums.length;
        if (nums[0] > target || nums[n - 1] < target) {
            return res;
        }


        /**
         * 二分找左边点
         */
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid; //因为要找左边点
            }
        }
        if (nums[start] == target) {
            res[0] = start;
        } else if (nums[end] == target) {
            res[0] = end;
        }


        /**
         * 二分找右边点
         */
        start = 0;
        end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else {
                start = mid; //因为要找右边点
            }
        }
        if (nums[end] == target) {
            res[1] = end;
        } else if (nums[start] == target) {
            res[1] = start;
        }

        return res;
    }


    public static void main(String[] args) {
        int[] a = searchRange(new int[]{3,3}, 3);
        System.out.println(a[0] + "  " + a[1]);
    }
}
