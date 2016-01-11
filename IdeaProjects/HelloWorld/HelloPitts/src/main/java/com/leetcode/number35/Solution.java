package com.leetcode.number35;

/**
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
 * You may assume no duplicates in the array.
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * 相当于,找到第一个大于target的位置,返回即可
     * 同样的, 二分查找!
     *
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;//肯定可以在第一个位置加嘛
        if (nums[0] > target) return 0;
        if (nums[nums.length - 1] < target) return nums.length;

        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            } else if (nums[mid] > target) {
                end = mid;
            } else {
                return mid;
            }
        }
        if (nums[start] == target) {
            return start;
        } else if (nums[end] == target) {
            return end;
        } else if (nums[start] > target) {
            return start;
        } else if (nums[end] < target) {
            return end + 1;
        } else {
            return end;
        }
    }



    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1,2,3,4,6}, 4));
    }
}
