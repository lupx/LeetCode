package com.leetcode.number226.InvertBinaryTree;

import java.util.Stack;

/**
 * Invert a binary tree.
          4
        /   \
       2     7
      / \   / \
     1   3 6   9

    to
          4
        /   \
       7     2
      / \   / \
     9   6 3   1
 * 说白了, 除叶子节点外, 每个节点交换其左右子树
 *
 * Created by Peixin Lu on 15/12/30.
 */
public class Solution {

    /**
     * 显然用递归做
     * 从根节点开始, 交换其左右孩子
     * beat 24.80%
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode tmpNode = root.left;
        root.left = root.right;
        root.right = tmpNode;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    public static void main(String[] args) {

    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
