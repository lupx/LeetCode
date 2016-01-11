package com.leetcode.number217.ContainsDuplicate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates.
 * Your function should return true if any value appears at least twice in the array,
 *    and it should return false if every element is distinct.
 *
 * Created by Peixin Lu on 15/12/28.
 */
public class Solution {

    /**
     * 两个方案:
     * (1)先排序, 再遍历, 时间复杂度O(nlogn) 肯定TLE, 下面的方案甚至有可能都TLE, 只能说这个题的test有问题!
     * (2)hashTable, 遍历, 时间复杂度O(n) beat 59.24%
     *
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        if (nums == null || nums.length == 0) return false;
        for(Integer i : nums) {
            if (!set.add(i)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
