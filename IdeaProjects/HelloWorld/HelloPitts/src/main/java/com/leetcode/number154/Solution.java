package com.leetcode.number154;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {

    /**
     * 二分法
     *
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        int n = nums.length;
        int start = 0;
        int end = n - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid - 1] > nums[mid]) return nums[mid];
            if (nums[mid + 1] < nums[mid]) return nums[mid + 1];
            if (nums[mid] < nums[n - 1]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (nums[start] < nums[end]) return nums[start];
        else return nums[end];
    }

    public static void main(String[] args) {
        int[] a = new int[]{4,5,6};
        System.out.println(findMin(a));
    }

}

