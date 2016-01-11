package com.leetcode.number47;

import java.util.*;

/**
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations.
 * For example,
 * [1,1,2] have the following unique permutations:
 * [1,1,2], [1,2,1], and [2,1,1].
 * Created by Peixin Lu on 15/10/10.
 */
public class Solution {

    /**
     * 还是回溯
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        if (nums == null || nums.length == 0) {
            return list;
        }
        Arrays.sort(nums);
        List<Integer> tmp = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(i, nums[i]);
//        }

        permuteHelper(nums, list, tmp, map);
        return list;
    }

    /**
     * 轮询数据做选择:
     * 每次遇到重复元素,且下一个元素不在map中的时候continue.
     * 最终i停留的位置就是最后一个不在map中的有效元素
     * 然后nums[i]s加入map,继续递归
     * 直到map大小和nums大小相同,说明已经找到了一个组合,加入list
     *
     * 注意, tmp和map要删除当前步加入的元素,才能进入下一次选择
     * @param nums
     * @param result 总结果
     * @param tmp 当前深搜保存的所有结果
     * @param map 已挑选的位置和数字
     * @return
     */
    public static void permuteHelper(int[] nums, List<List<Integer>> result, List<Integer> tmp, Map<Integer, Integer> map) {
        if (map.size() == nums.length) {
            result.add(new ArrayList<Integer>(tmp));
        }

        for (int i = 0; i < nums.length; i++) {
            //若还没有选相同元素,那么如果有重复,选出其中之一来就行了
            if (i < nums.length - 1
                    && nums[i] == nums[i + 1]
                    && !map.containsKey(i + 1)) {
                continue;//
            }
            if (map.containsKey(i)) {
                continue;
            }
            tmp.add(nums[i]);
            map.put(i, nums[i]);
            permuteHelper(nums, result, tmp, map);
            tmp.remove(tmp.size() - 1);
            map.remove(i);
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,3,2};
        List<List<Integer>> result = permuteUnique(nums);
        for (List<Integer> l : result) {
            StringBuilder sb = new StringBuilder();
            for (Integer in : l) {
                sb.append(in);
            }
            System.out.println(sb.toString());
        }
    }
}
