package com.leetcode.number103;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *     3
 *    / \
 *   9  20
 *     /  \
 *   15   7
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 *
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * 层序遍历, 只是加上层数的控制.
     * 还是从左往右遍历, 先加左子树, 再加右子树
     * 唯一的不同是, 奇数层, level出来后, reverse一下, 再加入rst
     * 运行时间: 3ms
     * @param root
     * @return
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;
        List<TreeNode> levelQ = new LinkedList<>();
        List<Integer> level = new ArrayList<>();
        levelQ.add(root);
        int levelOrder = 0;
        while (levelQ.size() > 0) {
            int i = 0;
            int len = levelQ.size();
            while (i < len) {
                TreeNode node = levelQ.remove(0);
                level.add(node.val);
                if (node.left != null) levelQ.add(node.left);
                if (node.right != null) levelQ.add(node.right);
                i++;
            }
            if (levelOrder % 2 != 0) {
                Collections.reverse(level);
            }
            rst.add(new ArrayList<>(level));
            level.clear();
            levelOrder++;
        }
        return rst;
    }


    public static void main(String[] args) {
    }
}

