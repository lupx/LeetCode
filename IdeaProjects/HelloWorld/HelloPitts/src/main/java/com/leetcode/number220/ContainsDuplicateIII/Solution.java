package com.leetcode.number220.ContainsDuplicateIII;

import java.util.TreeSet;

/**
 * Given an array of integers, find out whether there are two distinct indices i and j in the array
 *    such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k.
 *
 * 在nums中找两个数, 其下标之差不大于k, 其value之差不大于t
 * Created by Peixin Lu on 15/12/28.
 */
public class Solution {
    /**
     * 使用treeSet实现,比较简单!
     * treeSet有个很重要的方法是: subset(int left, int right)
     *   即在left和right之间找子集.
     *
     * beat 45.5%
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0) return false;
        TreeSet<Long> set = new TreeSet<>();

        //初始化treeset
        set.add((long) nums[0]);


        for (int i = 1; i < nums.length; i++) {
            //当i>k的时候, nums[i-k-1]的数已经根本不用做比较了, 所以直接remove掉
            if (i > k) set.remove((long) nums[i - k - 1]);
            //定义在nums[i]的情况下, 另一个数可能取值的最小/最大值
            long left = (long) nums[i] - t;
            long right = (long) nums[i] + t;
            //在已有集合中, 找到满足上面最小最大值中间的数
            if (left <= right && !set.subSet(left, right + 1).isEmpty()) return true;
            set.add((long) nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
