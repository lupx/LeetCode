package com.leetcode.number117;

import java.util.ArrayList;
import java.util.List;

/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *      1
 *    /  \
 *   2    3
 *  / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 *      1 -> NULL
 *    /  \
 *   2 -> 3 -> NULL
 *  / \    \
 * 4-> 5 -> 7 -> NULL
 *
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    public void connect(TreeLinkNode root) {
        if (root == null) return;
        List<TreeLinkNode> levelQ = new ArrayList<>();//层队列
        levelQ.add(root);
        while (levelQ.size() > 0) {
            int i = 0;
            int len = levelQ.size();
            while (i < len) {
                TreeLinkNode thisNode = levelQ.remove(0);
                if (i == len - 1) {
                    thisNode.next = null;
                } else {
                    thisNode.next = levelQ.get(0);
                }
                if (thisNode.left != null) levelQ.add(thisNode.left);
                if (thisNode.right != null) levelQ.add(thisNode.right);
                i++;
            }
        }
    }
}

