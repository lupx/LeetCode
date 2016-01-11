package com.leetcode.number96;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n.
 * For example,
 * Given n = 3, your program should return 5, because there are 5 unique BST's shown below.
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 *
 * Created by Peixin Lu on 15/10/22.
 */
public class Solution {

    /**
     * 解法一, 用递归来做
     * 此解法速度太慢, 因为很明显, 各种重算
     * @param n
     * @return
     */
    public static int numTrees(int n) {
        if (n <= 0) return 0;
        int sum = 0;
        return numTreesHelper(n, 1, n);
    }

    /**
     * 枚举i, 分为两边,分别递归求子树个数,左右两边结果相乘得到i为root的树的个数
     * @param n
     * @param start
     * @param end
     * @return
     */
    public static int numTreesHelper(int n, int start, int end) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        long sum = 0L;
        for (int i = start; i <= end; i++) {
            //对每个i,有左右两边
            long left = (long)numTreesHelper(i - start, start, i - 1);
            long right = (long)numTreesHelper(end - i, i + 1, end);
            if (left == 0) {
                sum += right;
            } else if (right == 0) {
                sum += left;
            } else {
                sum += left * right;
            }
        }
        return (int)sum;
    }


    /**
     * 解法二, 还是递归, 但是记忆化搜索, 我们每次计算的时候, 先看看之前有没有算过,要是算过直接取, 要是没算过, 我们就算一遍
     * 用一个hashMap来存结果. map<n, v>. n代表元素个数, v代表n个元素能组成的子树个数
     * @param n
     * @return
     */
    public static int numTreesMemorizeSearch(int n) {
        if (n <= 0) return 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<Integer,Integer>();
        return numTreesHelper(n, 1, n, map);
    }

    /**
     * 枚举i, 分为两边,分别递归求子树个数,左右两边结果相乘得到i为root的树的个数
     * @param n
     * @param start
     * @param end
     * @return
     */
    public static int numTreesHelper(int n, int start, int end, Map<Integer,Integer> map) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        long sum = 0L;
        for (int i = start; i <= end; i++) {
            //对每个i,有左右两边
            long left;
            long right;
            if (!map.containsKey(i - start)) {
                left = (long) numTreesHelper(i - start, start, i - 1, map);
                map.put(i - start, (int)left);
            } else {
                left = map.get(i - start);
            }
            if (!map.containsKey(end - i)) {
                right = (long) numTreesHelper(end - i, i + 1, end, map);
                map.put(end - i, (int)right);
            } else {
                right = map.get(end - i);
            }
            if (left == 0) {
                sum += right;
            } else if (right == 0) {
                sum += left;
            } else {
                sum += left * right;
            }
        }
        return (int)sum;
    }

    /**
     * 解法三, DP解法! 根据上面的记忆搜索可以得出这个解法.
     * 因为, 显然, 对于固定的n, 只有可能得出一个固定的结果.
     * 那么, 我们考虑构造DP函数, dp[i] = i个数能组成的子树个数
     * OJ上运行只用了0ms, 上面的记忆化搜索用2ms.
     * @param n
     * @return
     */
    public static int numTreesDP(int n) {
        if (n  < 2) return n;
        int[] dp = new int[n + 1];

        /**
         * 初始化
         */
        dp[0] = 1;//空节点组成的树应该为0个,但是为了下面的相乘运算, 写成1并不影响乘积.
        dp[1] = 1;

        /**
         * 从1到n, 分别算出dp[i], 最终dp[n]就是n个元素的所有可能BST树的个数
         * 那么枚举i代表n, 从1走到n.
         * 对每个i, 从0到i枚举j作为root, 对每个root, 左右两边的结果乘起来, 加入sum
         * 最终所有的sum存入dp[i], 代表i个元素时, 所有的BST个数
         */
        for (int i = 2; i <= n; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                //在i个数字内, 枚举j作为根
                //dp[j]就是左子树的所有可能
                //dp[i - j - 1]就是右子树的所有可能
                sum += dp[j] * dp[i - j - 1];
            }
            dp[i] = sum;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees(4));
    }
}

