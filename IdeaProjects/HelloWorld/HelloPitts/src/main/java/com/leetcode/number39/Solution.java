package com.leetcode.number39;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * Backtracking!
     * 首先排序
     * 如果target < 第一个数,直接返回空list
     *
     * 从0位开始, 选为当前数
     *
     * 当前数不停叠加,如果和等于target,那么当前数可以用. 回溯后, 还要选下一个数接着试.
     * 如果当前叠加>target了,说明当前数肯定不能用,回溯一位后,选下一个数接着试验.
     * 直到所有数试完.
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (candidates == null || candidates.length == 0) {
            return list;
        }

        Arrays.sort(candidates);
        if (target < candidates[0]) return list;

        /**
         * 对每一位数字,从自身选取开始递归, 不论返回什么, pick++,变为下一位继续递归, 直到结束
         */
        for (int i = 0; i < candidates.length; i++) {
            if (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) {
                //跳过重复元素
                continue;
            }
            if (candidates[i] == target) {
                //无需递归
                List<Integer> l = new LinkedList<Integer>();
                l.add(0, candidates[i]);
                list.add(l);
                continue;
            }
            for (int pick = i; pick < candidates.length; pick++) {
                List<List<Integer>> dfsResult = dfsHelper(candidates, candidates[i], pick, target);
                if (dfsResult != null && dfsResult.size() > 0) {
                    //递归有结果!
                    for (List<Integer> ls : dfsResult) {
                        ls.add(0, candidates[i]);
                        list.add(ls);
                    }
                }
            }
        }
        return list;
    }

    /**
     * pick意指当前方法需要选择的数的位置. 比如pick==0时,说明当前把0位数字叠加到tmpSum上去
     * 加完后,有几种可能性:
     * (1)加完后, tmpSum > target. 那么显然不合理, return空list
     * (2)加完后, tmpSum = target. 这就是答案之一了, 把当前数加入list, 返回
     * (3)加完后, tmpSum < target. 这个时候又有几个可能性:
     *    (3.1) target- tmpSum >= candidates[pick], 那么pick保持不变,深入一层即可;
     *    这里递归后有几个可能性:
     *      (3.1.1)如果返回了空list, 说明当前数字再选一次显然不可能了,那么就选下一位再递归试试;
     *      (3.1.2)如果返回了有内容的list, 说明走成功了, 把当前数字加入list, 然后选下一位继续递归尝试;
     *      最终使用当前位的所有可能性存入list中返回；
     *    (3.1) target - tmpSum < candidates[pick], 说明还差值小于当前值，绝对不可能成功，返回list
     * @param candidates
     * @param pick 当前选取的数字位, 也即当前叠加数字为candidates[pick]
     * @param tmpSum 临时结果
     * @param target
     * @return 当前走完后,如果最终成功,就返回一个list,包含了可能的数字组合
     */
    public static List<List<Integer>> dfsHelper(int[] candidates, int tmpSum, int pick, int target) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        tmpSum += candidates[pick];
        if (tmpSum > target) return result;
        if (tmpSum == target) {
            List<Integer> thisList = new LinkedList<Integer>();
            thisList.add(0, candidates[pick]);
            result.add(thisList);
            return result;
        }
        if (target - tmpSum >= candidates[pick]) {
            for (int i = pick; i < candidates.length; i++) {
                if (target - tmpSum - candidates[i] < 0) {
                    break;//为加快速度做的优化, 到达这个时候就不用再选下一位了
                }
                List<List<Integer>> r = dfsHelper(candidates, tmpSum, i, target);
                if (r.size() > 0) {
                    for (List<Integer> ls: r) {
                        ls.add(0, candidates[pick]);
                        result.add(ls);
                    }
                }
            }
        }
        return result;
    }


    public static void main(String[] args) {
        int[] candidates = new int[]{2,3,5};
        System.out.println(combinationSum(candidates, 5));
    }
}
