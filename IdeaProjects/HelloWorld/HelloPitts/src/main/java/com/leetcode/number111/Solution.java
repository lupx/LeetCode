package com.leetcode.number111;

/**
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
 * 最小深度是指, 从根节点走最少的节点到达叶子节点.
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 递归, 从根节点往下看, 返回左右子树的高度, 取其中小者+1, 为当前节点最小高度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;//叶子节点为1
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    public static void main(String[] args) {

    }
}

