package com.leetcode.number173;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *
 * 实现二叉搜索树的iterator类, 提供hasNext, next方法. 构造的时候应该使用BST的root进行构造
 * next()要求在O(1)时间, O(h)空间返回下一个最小的数字
 * hasNext()要求在O(1)时间, O(h)空间确认是否还有下一个节点
 *
 * 理论:
 * BST中,
 * 当前节点的下一个最小节点, 必定在当前节点左子树的最左下节点.
 * hasNext()方法实际是找当前树最右下节点, 如果当前节点就已经是最右下节点, 则hasNext()返回false, 其他情况下, 均返回true.
 *
 * 构造函数的主要职责是构造一个中序遍历
 * 借用数据结构: stack
 *
 *
 * Created by Peixin Lu on 15/11/27.
 */
public class BSTIterator {

    /**
     * to implement inorder traverse.
     */
    private Stack<TreeNode> treeStack;

    /**
     * Constructor, pass the root of the BST.
     * The main job here is to push all the treeNodes to the stack.
     * 算法:
     * 用递归把所有节点按中序遍历入栈!
     * @param root
     */
    public BSTIterator(TreeNode root) {
        treeStack = new Stack<>();
        BSTHelper(root);
    }

    private void BSTHelper(TreeNode root) {
        if (root == null) return;
        if (root.right != null) {
            BSTHelper(root.right);
        }
        treeStack.push(root);
        if (root.left != null) {
            BSTHelper(root.left);
        }
    }


    /**
     * 栈只要不空, 就还有元素
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return treeStack.isEmpty();
    }


    /**
     *
     * @return the next smallest number
     */
    public int next() {
        if (!treeStack.isEmpty()) {
            TreeNode node = treeStack.pop();
            return node.val;
        }
        return -1;
    }


    public static void main(String[] args) {
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

