package com.leetcode.number98;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * BST是否合法, 就是满足上面的定义即可
     * 解法一: 递归!
     * 首先, 左右子树都要是BST.
     * 同时, 当前root节点要大于左子树最大值, 同时要小于右子树最小值
     * OJ运行时间: 1ms
     * @param root
     * @return
     */
    public boolean isValidBSTRecursive(TreeNode root) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        if (root.left == null && root.right != null) {
            return isValidBSTRecursive(root.left) && isValidBSTRecursive(root.right)
                    && root.val < rightMin(root.right);
        } else if (root.left != null && root.right == null) {
            return isValidBSTRecursive(root.left) && isValidBSTRecursive(root.right)
                    && root.val > leftMax(root.left);
        } else {
            return isValidBSTRecursive(root.left) && isValidBSTRecursive(root.right)
                    && root.val > leftMax(root.left) && root.val < rightMin(root.right);
        }
    }

    /**
     * 递归求以root为根树的左子树中最大节点
     * DFS
     * @param root
     * @return
     */
    public int leftMax(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE;
        if (root.right == null) return root.val;
        return leftMax(root.right);
    }

    /**
     * 递归求以root为根树的右子树中最小节点
     * DFS
     * @param root
     * @return
     */
    public int rightMin(TreeNode root) {
        if (root == null) return Integer.MAX_VALUE;
        if (root.left == null) return root.val;
        return rightMin(root.left);
    }

    /**
     * 解法二: 中序优先遍历, 把节点存入一个ArrayList中
     * 然后,遍历list, 只要是非减序列,就返回true
     * OJ运行 5ms
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        List<Integer> list = new ArrayList<Integer>();
        inOrderTraverse(root, list);
        for (int i = 0; i < list.size(); i++) {
            if (i > 0 && list.get(i) <= list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }

    public void inOrderTraverse(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inOrderTraverse(root.left, list);
        list.add(root.val);
        inOrderTraverse(root.right, list);
    }

    public static void main(String[] args) {
    }
}

