package com.leetcode.number161;

/**
 * A peak element is an element that is greater than its neighbors.
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.
 * The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.
 * You may imagine that num[-1] = num[n] = -∞.
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.
 *
 * Created by Peixin Lu on 15/11/23.
 */
public class Solution {

    /**
     * 二分法
     * 切一刀, 然后看看和两边比一下,
     *    如果比两边都大, 那就更新到peak里
     *    如果比两边都小, 或者一个大一个小, 说明不是peak, 决定下start和end怎么弄, 然后继续切
     * beats 31.16%
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (mid == 0 && nums[mid] > nums[mid + 1]) {
                return mid;
            }
            if (mid == nums.length - 1 && nums[mid] > nums[mid - 1]) {
                return mid;
            }
            if (nums[mid] > nums[mid - 1]
                    && nums[mid] > nums[mid + 1]) {
                return mid;
            } else if (nums[mid] > nums[mid - 1]
                    && nums[mid] < nums[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] > nums[end]) return start;
        else return end;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 1};
        System.out.println(findPeakElement(a));
    }
}

