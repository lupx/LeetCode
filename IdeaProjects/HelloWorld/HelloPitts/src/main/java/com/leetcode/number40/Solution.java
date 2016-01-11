package com.leetcode.number40;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 * Created by Peixin Lu on 15/10/5.
 */
public class Solution {

    /**
     * 题目要求换句话说就是, 每一位的数字,只能出现一次.
     * 如题目例子, 1在不同的位置出现了2次, 所以,结果里1,1,6就可以出现2个1
     * 除此而外,决不允许有重复数字在每个结果里.
     *
     * 肯定还是Backtracking!
     * 首先排序
     * 如果target < 第一个数,直接返回空list
     *
     * 从0位开始, 选为当前数
     * 选好当前数后, 再选下一个pick位置, 然后进入dfshelper深搜递归
     * 同时注意:
     * 每次选当前数的时候,如果当前数之前已经有用过直接continue.
     * 每次选下一个pick位置的时候, 如果之前有用过,也continue.
     *
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new LinkedList<List<Integer>>();
        if (candidates == null || candidates.length == 0) {
            return list;
        }

        Arrays.sort(candidates);
        if (target < candidates[0]) return list;

        /**
         * 对每一位数字,从下一位起选取, 选到合适的就开始递归, 不论返回什么, pick++,变为下一位继续递归, 直到结束
         */
        int lastI = Integer.MAX_VALUE;
        for (int i = 0; i < candidates.length; i++) {
            //保证重复数字里,只会选择第一个作为对象考虑, 之后的全部跳过
            if (lastI == candidates[i]) {
                continue;
            } else {
                lastI = candidates[i];
            }

            if (candidates[i] == target) {
                //无需递归
                List<Integer> l = new LinkedList<Integer>();
                l.add(0, candidates[i]);
                list.add(l);
                continue;
            }
            int lastPick = Integer.MAX_VALUE;
            for (int pick = i + 1; pick < candidates.length; pick++) {
                if (lastPick == candidates[pick]) {
                    continue;
                } else {
                    lastPick = candidates[pick];
                }
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
            int lastPick = Integer.MAX_VALUE;
            for (int i = pick + 1; i < candidates.length; i++) {
                if (lastPick == candidates[i]) {
                    continue;
                } else {
                    lastPick = candidates[i];
                } //避免选择同样的数字
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
        int[] candidates = new int[]{5,4,5,1,5,3,1,4,5,5,4};
        System.out.println(combinationSum2(candidates, 10));
    }
}
