package com.leetcode.number116;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree
 * struct TreeLinkNode {
 * TreeLinkNode *left;
 * TreeLinkNode *right;
 * TreeLinkNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 * Initially, all next pointers are set to NULL.
 * Note:
 * You may only use constant extra space.
 * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 * For example,
 * Given the following perfect binary tree,
 *      1
 *    /  \
 *   2    3
 *  / \  / \
 * 4  5  6  7
 * After calling your function, the tree should look like:
 *      1 -> NULL
 *    /  \
 *   2 -> 3 -> NULL
 *  / \  / \
 * 4->5->6->7 -> NULL
 *
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * 其实就是个层序遍历, 然后对每一层元素从左往右改链, 最后一个节点next指向null.
     * 10ms.
     * @param root
     */
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

