package com.leetcode.number216.CombinationSumIII;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find all possible combinations of k numbers that add up to a number n,
 *    given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
 * Ensure that numbers within the set are sorted in ascending order.
 *
 * Example 1:
 * Input: k = 3, n = 7
 * Output:
 * [[1,2,4]]
 *
 * Example 2:
 * Input: k = 3, n = 9
 * Output:
 * [[1,2,6], [1,3,5], [2,3,4]]
 * Created by Peixin Lu on 15/12/26.
 */
public class Solution {

    /**
     * 递归回溯
     * beat 58.91%
     * @param k
     * @param n
     * @return
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> rst = new ArrayList<>();
        List<Integer> tmp = new ArrayList<>();

        if (n == 0) return rst;
        helper(k, n, 1, tmp, rst);
        return rst;
    }

    private static void helper(int k, int n, int start, List<Integer> tmp, List<List<Integer>> rst) {
        if (tmp.size() == k) {
            if (n == 0) rst.add(new ArrayList<>(tmp));
        } else {
            //k还有名额
            for (int i = start; i < 10; i++) {
                tmp.add(i);
                n -= i;
                if (n < 0) {
                    tmp.remove(tmp.size() - 1);
                    break;
                }
                helper(k, n, i + 1, tmp, rst);
                tmp.remove(tmp.size() - 1);
                n += i;
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> rst = combinationSum3(3, 9);
        for (List<Integer> list : rst) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : list) {
                sb.append(i).append(",");
            }
            System.out.println(sb.toString());
        }
    }
}
