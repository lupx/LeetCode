package com.leetcode.number78;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Created by PeixinLu on 15/10/16.
 */
public class Solution {

    /**
     * 实际上,就是穷举所有组合,加入result里,
     * 最后返回result
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) return result;
        List<Integer> empty = new ArrayList<Integer>();
        result.add(empty);//空集合肯定有
        Arrays.sort(nums);
        for (int i = 1; i <= nums.length; i++) {
            //分别求组合
            combineHelper(nums, i, result, empty, 0);
        }
        return result;
    }

    public static void combineHelper(int[] nums, int k, List<List<Integer>> result, List<Integer> tmp, int start) {
        if (tmp.size() == k) {
            result.add(new ArrayList<Integer>(tmp));
            return;
        }
        if (start >= nums.length) return;
        for (int i = start; i < nums.length; i++) {
            if (tmp.size() != 0
                    && tmp.get(tmp.size() - 1) == nums[i]) {
                //该元素已选过
                continue;
            } else {
                tmp.add(nums[i]);
                combineHelper(nums, k, result, tmp, i + 1);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> list = subsets(new int[]{1,2,3});
        System.out.println(list.size() + "个组合:");
        for (List<Integer> l : list) {
            System.out.println(l.size() + "个元素:");
            StringBuilder sb = new StringBuilder();
            for (Integer i : l) {
                sb.append(i);
            }
            System.out.println(sb.toString() + "\n");
        }
    }
}

