package com.leetcode.number110;

import com.leetcode.number109.ListNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of
 *   every node never differ by more than 1
 * 判断给定树是否是平衡二叉树: 任何一个非叶节点 左右子树高度不大于1.
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 递归:
     * 左右子树高度差<=1, 就认为当前平衡
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int leftDepth = root.left == null? -1 : depth(root.left);
        int rightDepth = root.right == null? -1 : depth(root.right);
        if (leftDepth - rightDepth > 1
                || rightDepth - leftDepth > 1
                || leftDepth == Integer.MAX_VALUE
                || rightDepth == Integer.MAX_VALUE) {
            return false;
        }
        return true;
    }

    private int depth(TreeNode root) {
        if (root == null) return -1;
        int leftDepth = root.left == null? -1 : depth(root.left);
        int rightDepth = root.right == null? -1 : depth(root.right);
        if (leftDepth - rightDepth > 1
                || rightDepth - leftDepth > 1
                || leftDepth == Integer.MAX_VALUE
                || rightDepth == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
    }
}

