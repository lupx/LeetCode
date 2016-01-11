package com.leetcode.number90;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
 * Note:
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example,
 * If nums = [1,2,2], a solution is:
 * [
 * [2],
 * [1],
 * [1,2,2],
 * [2,2],
 * [1,2],
 * []
 * ]
 * Created by PeixinLu on 15/10/21.
 */
public class Solution {

    /**
     * 有重复数字的子集合,回溯递归做
     * 注意到重复数字, 所以不能用map来存.
     * 其实就是从0->nums.len求全部排列
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums);
        for (int n = 0; n <= nums.length; n++) {
            List<Integer> tmp = new ArrayList<Integer>();
            permutationHelper(nums, result, tmp, 0, n);
        }
        return result;
    }

    /**
     * 求nums中, n个元素的排列
     * @param nums
     * @param result
     * @param tmp
     * @param start
     */
    public static void permutationHelper(int[] nums, List<List<Integer>> result,
                                         List<Integer> tmp, int start, int n) {
        if (n == 0) {
            result.add(new ArrayList<>());
        } else {
            //核心在这里
            if (tmp.size() == n) {//base condition
                result.add(new ArrayList<>(tmp));
            } else {
                if (n == nums.length) {
                    //for speed consideration
                    for (int i = 0; i < nums.length; i++) {
                        tmp.add(nums[i]);
                    }
                    result.add(new ArrayList<>(tmp));
                    return;
                }
                for (int i = start; i < nums.length; i++) {
                    if (i == start || nums[i] != nums[i - 1]) {
                        tmp.add(nums[i]);
                        permutationHelper(nums, result, tmp, i + 1, n);
                        tmp.remove(tmp.size() - 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2,2,3};
        List<List<Integer>> list =subsetsWithDup(nums);
        for (List<Integer> l : list) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : l) {
                sb.append(i).append(",");
            }
            System.out.println(sb.toString());
        }
    }
}

