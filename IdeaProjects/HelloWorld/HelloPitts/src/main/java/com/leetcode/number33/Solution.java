package com.leetcode.number33;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 * You may assume no duplicate exists in the array.
 *
 * Created by PeixinLu on 15/10/5.
 */
public class Solution {

    /**
     * 暴力办法都可以在O(n)内找到,但是这是个hard难度的题.
     * 根据九章算法班的经验来看, O(n)如果还要优化,只能用一个办法: 二分搜索!
     * 那其实就是在两段递增数列里找target, 重点在于, 这两段递增数列的分界点在哪里?
     * 找到分界点, 其实就是2次二分就解决了!
     * 分界点,就是最小的那个数!
     * 好吧, 先找分界点, 首先不管三七二十一,先在中间砍一刀得到二分点mid, 有几种可能性:
     * (1) mid落入了4567这个区间, 如何判断出来的? nums[mid+1] > nums[mid]
     *    此时, 可以肯定, 最小点一定在这个区间后面, 那么start = mid, 再二分
     * (2) mid落入了012这个区间, 显然nums[mid] > nums[mid+1]
     *    此时, 可以肯定, 最小点一定在start->mid之间, 那么end = mid, 再二分
     *
     * 一直到最后, 找到分界点.
     * 找到分界点后,就简单了,就是最简单的在递增序列里二分找数.
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return target == nums[0] ? 0 : -1;
        if (nums.length == 2) {
            if (nums[0] == target) return 0;
            if (nums[1] == target) return 1;
            else return -1;
        }

        int n = nums.length;
        int start = 0;
        int end = nums.length - 1;
        int realMid = Integer.MAX_VALUE;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;//avoid overflow
            if (nums[mid] > nums[0]) {
                //落入了4567中间
                start = mid;
            } else if (nums[mid] < nums[n - 1]) {
                //落入了012中间
                end = mid;
            } else if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
                realMid = mid + 1;
                break;
            } else {
                realMid = mid;
                break;
            }
        }
        if (realMid == Integer.MAX_VALUE) {
            if (nums[start] > nums[end]) {
                realMid = end;
            } else {
                realMid = start;
            }
        }

        //到这里就找到了realMid分界点, 注意这里的条件!
        if (target >= nums[realMid] && target <= nums[nums.length - 1]) {
            start = realMid;
            end = nums.length - 1;
        } else {
            start = 0;
            end = realMid;
        }

        if (start == end) return nums[start] == target ? start : -1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > target) {
                end = mid;
            } else if (nums[mid] < target) {
                start = mid;
            } else {
                return mid;
            }
        }

        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(search(new int[]{9,1,2,3,4,5,6,7,8}, 9));
    }
}
