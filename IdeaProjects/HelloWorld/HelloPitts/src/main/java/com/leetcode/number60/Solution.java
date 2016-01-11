package com.leetcode.number60;


import java.util.*;

/**
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * By listing and labeling all of the permutations in order,
 * We get the following sequence (ie, for n = 3):
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * Given n and k, return the kth permutation sequence.
 * Note: Given n will be between 1 and 9 inclusive.
 *
 * Created by Peixin Lu on 15/10/12.
 */
public class Solution {

    /**
     * 获取n个数字所有排序组合中第k个组合返回
     * 方案1: 穷举所有排列, 然后从中取出第k个就行了
     * 方案2: 能否根据排序排列的性质,直接选出所求结果?
     * n的所有排序排列的个数 = n * (n-1的所有排序排列个数)
     * 那么, 所有排列可以分为n个组, 每个组里元素个数 = n-1个元素的所有排列个数
     * 那么, k换算成k', 可以看做是在n-1个元素的排列里找第k'个数
     * 继续划分, 知道k' = 1, 此时第一个排列求出来,即可.
     *
     *
     * @param n
     * @param k
     * @return
     */
    public static String getPermutation(int n, int k) {
        /**
         * 根据测试, k <= total, 所以不会有异常输入
         */
        StringBuilder sb = new StringBuilder();
        List<Integer> ints = new LinkedList<Integer>();
        for (int i = 1; i <= n; i++) {
            ints.add(i);
        }
        while (n > 0) {
            int total = factorial(n);
            int quota = k / (total / n);
            int remain = k % (total / n);
            if (quota < 1 || k == (total / n)) {
                //要找的就在当前第一组
                sb.append(ints.get(0));
                //k不变, ints减去第一个元素, 然后排序
                ints.remove(0);
                n--;
            } else {
                //在当前第quota + 1个组里找k =  k - quota * (total / n)
                //更新k =  k - quota * (total / n)
                //在ints里找第quota个元素,append到sb中
                k = remain;
                if (k == 0) {
                    //特殊情况, k刚好是某一组最后一个元素
                    sb.append(ints.get(quota - 1));
                    ints.remove(quota - 1);
                    //剩余元素从大到小排序,append输出即可
                    Collections.reverse(ints);
                    for (Integer i : ints) {
                        sb.append(i);
                    }
                    break;
                }
                sb.append(ints.get(quota));
                ints.remove(quota);
                if (k == 1) {
                    for (Integer i : ints) {
                        sb.append(i);
                    }
                    break;
                }
//                Collections.sort(ints);
                n--;
            }
        }
        return sb.toString();
    }

    public static int factorial(int n) {
        int result = 1;
        while (n > 0) {
            result *= n;
            n--;
        }
        return result;
    }

    public static void main (String[] args) {
//        System.out.println(factorial(5));
        System.out.println(getPermutation(9,48));
    }

}
