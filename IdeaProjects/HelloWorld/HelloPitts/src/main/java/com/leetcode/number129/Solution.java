package com.leetcode.number129;

/**
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 *       1
 *      / \
 *     2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 *
 * Created by Peixin Lu on 15/11/6.
 */
public class Solution {

    /**
     * DFS递归求解:
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        StringBuilder sb = new StringBuilder();
        return helper(root, sb);
    }

    private int helper(TreeNode node, StringBuilder sb) {
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            String s = new String(sb);
            sb.deleteCharAt(sb.length() - 1);
            return Integer.valueOf(s);
        }
        int sum = 0;
        if (node.left != null) {
            sum += helper(node.left, sb);
        }
        if (node.right != null) {
            sum += helper(node.right, sb);
        }
        sb.deleteCharAt(sb.length() - 1);
        return sum;
    }

    public static void main(String[] args) {

    }
}

