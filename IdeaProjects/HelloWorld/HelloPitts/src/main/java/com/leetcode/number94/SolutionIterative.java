package com.leetcode.number94;

import java.util.*;

/**
 * 循环迭代的方式做树的中序遍历
 * Created by PeixinLu on 15/10/22.
 */
public class SolutionIterative {
    /**
     * 循环迭代, 用栈做
     * @param root
     * @return
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Integer> visited = new HashMap<TreeNode, Integer>();
        if (root == null) return result;
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.peek();
            if (node.left != null && !visited.containsKey(node.left)) {
                stack.push(node.left);
                continue;
            }
            result.add(node.val);
            visited.put(node, 1);
            stack.pop();
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        return result;
    }
}
