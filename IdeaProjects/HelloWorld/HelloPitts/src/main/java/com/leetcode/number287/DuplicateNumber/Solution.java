package com.leetcode.number287.DuplicateNumber;


import java.util.Arrays;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 *   prove that at least one duplicate number must exist.
 * Assume that there is only one duplicate number, find the duplicate one.
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 *
 * Created by Peixin Lu on 16/1/9.
 */
public class Solution {

    /**
     * 先排序,然后遍历一遍看谁出现了2次以上,就返回
     * beat 15.92%
     * 但是不符合题目要求!
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            if (nums[i] == nums[i + 1]) return nums[i];
        }
        return 0;
    }

    /**
     * 每次找到mid, 然后看看nums[mid]和start以及end比较一下, 算算里面可以有的最大元素个数maxElement:
     * 如果某区间(start->mid, 或者 mid->end)实际元素个数<最大元素个数, 那么重复元素肯定不在这个区间
     * @param nums
     * @return
     */
    public static int findDuplicateBinarySearch(int[] nums) {
     if (nums == null || nums.length == 0)
        return 0;
        int start = 1, end = nums.length - 1, mid;
        while (start < end) {
            mid = start + (end - start) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            if (count > mid)
                end = mid;
            else
                start = mid + 1;
        }
        return start;
    }

    /**
     * 使用链表找环的思想来做
     * @param nums
     * @return
     */
    public static int findDuplicateLinkedListFindCycle(int[] nums) {
        int slow = 0;
        int fast = 0;
        int finder = 0;

        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast)
                break;
        }

        while (true) {
            slow = nums[slow];
            finder = nums[finder];
            if (slow == finder)
                return slow;
        }
    }

    public static void main(String[] args) {
        System.out.println(findDuplicateBinarySearch(new int[]{9,2,9,5,6,  7,8,3,9,1}));
    }
}
