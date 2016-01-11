package com.leetcode.number144;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 *      1
 *      \
 *      2
 *      /
 *      3
 * return [1,2,3].
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 要求不用递归实现树的先序遍历
     * 用栈来做:
     * 对每个节点, 右子入栈, 左子再入栈. 然后出栈一个, 置为当前节点, 循环
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode thisNode = stack.pop();
            rst.add(thisNode.val);
            if (thisNode.right != null) stack.push(thisNode.right);
            if (thisNode.left != null) stack.push(thisNode.left);
        }
        return rst;
    }

    public static void main(String[] args) {
    }
}

