package com.leetcode.number257.BinaryTreePaths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a binary tree, return all root-to-leaf paths.
 * For example, given the following binary tree:
 *
 *       1
 *     /   \
 *    2     3
 *    \
 *     5
 * All root-to-leaf paths are:
 * ["1->2->5", "1->3"]
 *
 * Created by Peixin Lu on 16/1/1.
 */
public class Solution {

    /**
     * 递归求解, 深搜思想.
     * beat 12.96%
     * @param root
     * @return
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> rst = new ArrayList<>();
        if (root == null) return rst;
        StringBuilder tmp = new StringBuilder();
        helper(root, rst, tmp);
        return rst;
    }

    private void helper(TreeNode root, List<String> rst, StringBuilder tmp) {
        StringBuilder newStr = new StringBuilder(tmp);
        newStr.append(root.val);
        if (root.left == null && root.right == null) {
            //到头了
            rst.add(newStr.toString());
        } else {
            newStr.append("->");
            if (root.left != null) helper(root.left, rst, newStr);
            if (root.right != null) helper(root.right, rst, newStr);
        }
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