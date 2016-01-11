package com.leetcode.number155;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * Find the minimum element.
 * The array may contain duplicates.
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {

    /**
     *
     * 如果包含相等元素, 那么二分法就失效了, 只能是O(n)时间
     * @param nums
     * @return
     */
    public int findMin(int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (min > nums[i]) min = nums[i];
        }
        return min;
    }


    public static void main(String[] args) {
        int[] a = new int[]{4,5,6};
    }

}

