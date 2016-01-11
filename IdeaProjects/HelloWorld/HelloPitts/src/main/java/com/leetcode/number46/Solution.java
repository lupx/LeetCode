package com.leetcode.number46;

import java.util.*;

/**
 * Given a collection of numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 *
 * Created by Peixin Lu on 15/10/10.
 */
public class Solution {


    /**
     * 求所有结果的,肯定不能用DP
     * 相反,这种题目, 最好的办法是递归回溯.
     * 同时,和位置无关,可以先排序,排序后再找可以避免重复
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        permuteHelper(nums, list, tmp);
        return list;
    }

    /**
     * 每一次都从cands中remove出一个数, 放入tmp中, 然后继续递归
     * 如果cands中无数了,就把tmp加入list中,然后return
     * @param nums
     * @param list
     * @return
     */
    public static void permuteHelper(int[] nums, List<List<Integer>> list, List<Integer> tmp) {
        if (tmp.size() == nums.length) {
            list.add(new ArrayList<Integer>(tmp));//defensive copy
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (tmp.contains(nums[i])) {
                continue;
            }
            tmp.add(nums[i]);
            permuteHelper(nums, list, tmp);
            tmp.remove(tmp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1};
        List<List<Integer>> result = permute(nums);
        for (List<Integer> l : result) {
            StringBuilder sb = new StringBuilder();
            for (Integer in : l) {
                sb.append(in);
            }
            System.out.println(sb.toString());
        }
    }
}
