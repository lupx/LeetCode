package com.leetcode.number145;

import java.util.*;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * For example:
 * Given binary tree {1,#,2,3},
 *      1
 *      \
 *       2
 *      /
 *     3
 * return [3,2,1].
 * Note: Recursive solution is trivial, could you do it iteratively?
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * 不用递归实现后序遍历!
     * 还是用栈实现:
     * 从root开始, 只要有左右子节点任意一个, 当前节点入栈, 右子入栈, 左子再入栈.
     * stack.peek()看该节点是否有左右子:
     *   若有, 继续按照上面流程入栈
     *   若没有左子, 右子入栈
     *   若没有右子, 左子入栈
     *   若两子都没有, 该节点弹栈加入rst
     *
     * 注意,需要一个hashset记录节点是否被访问过了
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;

        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();//记录是否访问过

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek.right != null
                    && !visited.contains(peek.right)) stack.push(peek.right);
            if (peek.left != null
                    && !visited.contains(peek.left)) stack.push(peek.left);
            if (stack.peek() == peek) {
                TreeNode node = stack.pop();
                rst.add(node.val);//出栈, 加入结果集
                visited.add(node);//记录
            }
        }
        return rst;
    }

    public static void main(String[] args) {
    }
}

