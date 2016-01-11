package com.leetcode.number199;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.
 * For example:
 * Given the following binary tree,
 *     1            <---
 *   /   \
 *  2     3         <---
 *  \     \
 *   5     4       <---
 * You should return [1, 3, 4].
 * Created by Peixin Lu on 15/12/2.
 */
public class Solution {

    /**
     * 层序遍历, 每一层最后一个元素加入list
     * beat 10.92%
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rst = new ArrayList<>();
        if (root == null) return rst;
        List<TreeNode> levelQ = new LinkedList<>();
        levelQ.add(root);
        while (!levelQ.isEmpty()) {
            int start = 0;
            int end = levelQ.size();
            while (start < end) {
                TreeNode thisNode = levelQ.remove(0);
                if (thisNode.left != null) levelQ.add(thisNode.left);
                if (thisNode.right != null) levelQ.add(thisNode.right);
                if (start == end - 1) rst.add(thisNode.val);
                start++;
            }
        }
        return rst;
    }

    public static void main(String[] args) {

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}

