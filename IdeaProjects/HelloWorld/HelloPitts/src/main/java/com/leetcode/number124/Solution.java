package com.leetcode.number124;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a binary tree, find the maximum path sum.
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections.
 * The path does not need to go through the root.
 *
 * For example:
 * Given the below binary tree,
 *       1
 *      / \
 *     2  3
 * Return 6.
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    private static int max = Integer.MIN_VALUE;

    /**
     * 递归DFS:
     * 1. 左右两个子树内的maxPath求出来
     * 2. 左右两个子树跨越root的maxPath求出来
     * 3. 做比较, 取大者
     * @param root
     * @return
     */
    public static int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    /**
     * 从root到leaf, 最大的一条线的长度
     * @param root
     * @return
     */
    private static int helper(TreeNode root) {
        if (root == null) return 0;
        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        root.left = left;
        root.right = right;
        System.out.println(maxPathSum(root));
    }
}

