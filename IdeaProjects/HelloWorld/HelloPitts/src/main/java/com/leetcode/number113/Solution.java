package com.leetcode.number113;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, flatten it to a linked list in-place.
 * 把一棵树转变成一个"链表", 常数空间
 * Created by Peixin Lu on 15/10/29.
 */
public class Solution {

    /**
     * flatten tree其实就是原树的前序序列直接构成的树
     * 递归求解:
     * 对每个root, right指向left, 然后当前right的最后一个元素.right指向之前的right
     * 225testcases, 2ms
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        if (root.left != null) flatten(root.left);
        if (root.right != null) flatten(root.right);
        if (root.left != null) {
            TreeNode newRight = root.right;
            root.right = root.left;
            root.left = null;
            TreeNode tmp = root.right;
            while (tmp != null && tmp.right != null) tmp = tmp.right;//找到最右子节点
            tmp.right = newRight;
        }
    }

    public static void main(String[] args) {

    }
}

