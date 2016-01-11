package com.leetcode.number104;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Created by Peixin Lu on 15/10/24.
 */
public class Solution {

    /**
     * DFS思想, 递归来做:
     * 其实是比较两个子树各自的距离, 大者+1, 就是当前节点的的最远距离
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }


    /**
     * 迭代来做, 其实还是层序的思想
     * 3ms.
     * @param root
     * @return
     */
    public int maxDepthIterative(TreeNode root) {
        if (root == null) return 0;
        List<TreeNode> levelQ = new ArrayList<TreeNode>();//层队列
        levelQ.add(root);
        int depth = 1;
        while (levelQ.size() > 0) {
            int i = 0;
            int len = levelQ.size();
            boolean isDeeper = false;
            while (i < len) {
                TreeNode thisNode = levelQ.remove(0);
                if (thisNode.left != null) {
                    levelQ.add(thisNode.left);
                    isDeeper = true;
                }
                if (thisNode.right != null) {
                    levelQ.add(thisNode.right);
                    isDeeper = true;
                }
                i++;
            }
            if (isDeeper) {
                depth++;
            }
        }
        return depth;
    }

    public static void main(String[] args) {
    }
}

