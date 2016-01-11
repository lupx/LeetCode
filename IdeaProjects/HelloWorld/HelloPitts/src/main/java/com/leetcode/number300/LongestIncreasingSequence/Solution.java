package com.leetcode.number300.LongestIncreasingSequence;


/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length.
 * Your algorithm should run in O(n^2) complexity.
 *
 * Follow up: Could you improve it to O(n log n) time complexity?
 * 首先要求用O(n^2)做出来, 然后follow up要求用O(nlogn)做
 *
 * Created by Peixin Lu on 16/1/10.
 */
public class Solution {

    /**
     * O(n^2)解法:
     * dp, 两层遍历
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int N = nums.length;
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,4}));
    }
}
