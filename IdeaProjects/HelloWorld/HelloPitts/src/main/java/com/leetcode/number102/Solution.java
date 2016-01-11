package com.leetcode.number102;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *    3
 *   / \
 *  9  20
 *  /  \
 * 15   7
 * return its level order traversal as:
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 层序遍历, 用队列
     * 4ms, 好像有点慢.
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<List<Integer>>();
        if (root == null) return rst;
        List<TreeNode> levelQ = new LinkedList<>();
        List<Integer> level = new ArrayList<>();
        levelQ.add(root);
        while (levelQ.size() > 0) {
            int i = 0;
            int len = levelQ.size();
            while (i < len) {
                TreeNode thisNode = levelQ.remove(0);
                level.add(thisNode.val);
                if (thisNode.left != null) levelQ.add(thisNode.left);
                if (thisNode.right != null) levelQ.add(thisNode.right);
                i++;
            }
            rst.add(new ArrayList<>(level));
            level.clear();
        }
        return rst;
    }

    public static void main(String[] args) {
    }
}

