package com.leetcode.number77;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example,
 * If n = 4 and k = 2, a solution is:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 * Created by PeixinLu on 15/10/16.
 */
public class BTSolution {

    /**
     * 组合题目, C(n, k)
     * 回溯递归
     *
     * @param n
     * @param k
     * @return
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n == 0 || k == 0 || k > n) return result;
        List<Integer> tmp = new ArrayList<Integer>();
        combineHelper(n, k, result, tmp);
        return result;
    }

    public static void combineHelper(int n, int k, List<List<Integer>> result, List<Integer> tmp) {
        if (tmp.size() == k) {
            result.add(new ArrayList<Integer>(tmp));
            return;
        }
        for (int i = tmp.size() == 0 ? 1 :
                tmp.get(tmp.size() - 1) + 1; i <= n; i++) {
            if (tmp.size() != 0
                    && tmp.get(tmp.size() - 1) == i) {
                //该元素已选过
                continue;
            } else {
                tmp.add(i);
                combineHelper(n, k, result, tmp);
                tmp.remove(tmp.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> list = combine(6, 3);
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

